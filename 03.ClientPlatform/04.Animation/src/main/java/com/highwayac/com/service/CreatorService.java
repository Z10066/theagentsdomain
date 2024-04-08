package com.highwayac.com.service;

import com.highwayac.com.repository.CreatorRepository;
import com.highwayac.com.service.dto.CreatorDTO;
import com.highwayac.com.service.mapper.CreatorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link com.highwayac.com.domain.Creator}.
 */
@Service
@Transactional
public class CreatorService {

    private final Logger log = LoggerFactory.getLogger(CreatorService.class);

    private final CreatorRepository creatorRepository;

    private final CreatorMapper creatorMapper;

    public CreatorService(CreatorRepository creatorRepository, CreatorMapper creatorMapper) {
        this.creatorRepository = creatorRepository;
        this.creatorMapper = creatorMapper;
    }

    /**
     * Save a creator.
     *
     * @param creatorDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<CreatorDTO> save(CreatorDTO creatorDTO) {
        log.debug("Request to save Creator : {}", creatorDTO);
        return creatorRepository.save(creatorMapper.toEntity(creatorDTO)).map(creatorMapper::toDto);
    }

    /**
     * Update a creator.
     *
     * @param creatorDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<CreatorDTO> update(CreatorDTO creatorDTO) {
        log.debug("Request to update Creator : {}", creatorDTO);
        return creatorRepository.save(creatorMapper.toEntity(creatorDTO)).map(creatorMapper::toDto);
    }

    /**
     * Partially update a creator.
     *
     * @param creatorDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<CreatorDTO> partialUpdate(CreatorDTO creatorDTO) {
        log.debug("Request to partially update Creator : {}", creatorDTO);

        return creatorRepository
            .findById(creatorDTO.getId())
            .map(existingCreator -> {
                creatorMapper.partialUpdate(existingCreator, creatorDTO);

                return existingCreator;
            })
            .flatMap(creatorRepository::save)
            .map(creatorMapper::toDto);
    }

    /**
     * Get all the creators.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<CreatorDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Creators");
        return creatorRepository.findAllBy(pageable).map(creatorMapper::toDto);
    }

    /**
     *  Get all the creators where Video is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<CreatorDTO> findAllWhereVideoIsNull() {
        log.debug("Request to get all creators where Video is null");
        return creatorRepository.findAllWhereVideoIsNull().map(creatorMapper::toDto);
    }

    /**
     * Returns the number of creators available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return creatorRepository.count();
    }

    /**
     * Get one creator by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<CreatorDTO> findOne(Long id) {
        log.debug("Request to get Creator : {}", id);
        return creatorRepository.findById(id).map(creatorMapper::toDto);
    }

    /**
     * Delete the creator by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete Creator : {}", id);
        return creatorRepository.deleteById(id);
    }
}
