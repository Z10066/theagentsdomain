package com.highwayac.com.service;

import com.highwayac.com.repository.CopyrightRepository;
import com.highwayac.com.service.dto.CopyrightDTO;
import com.highwayac.com.service.mapper.CopyrightMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link com.highwayac.com.domain.Copyright}.
 */
@Service
@Transactional
public class CopyrightService {

    private final Logger log = LoggerFactory.getLogger(CopyrightService.class);

    private final CopyrightRepository copyrightRepository;

    private final CopyrightMapper copyrightMapper;

    public CopyrightService(CopyrightRepository copyrightRepository, CopyrightMapper copyrightMapper) {
        this.copyrightRepository = copyrightRepository;
        this.copyrightMapper = copyrightMapper;
    }

    /**
     * Save a copyright.
     *
     * @param copyrightDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<CopyrightDTO> save(CopyrightDTO copyrightDTO) {
        log.debug("Request to save Copyright : {}", copyrightDTO);
        return copyrightRepository.save(copyrightMapper.toEntity(copyrightDTO)).map(copyrightMapper::toDto);
    }

    /**
     * Update a copyright.
     *
     * @param copyrightDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<CopyrightDTO> update(CopyrightDTO copyrightDTO) {
        log.debug("Request to update Copyright : {}", copyrightDTO);
        return copyrightRepository.save(copyrightMapper.toEntity(copyrightDTO)).map(copyrightMapper::toDto);
    }

    /**
     * Partially update a copyright.
     *
     * @param copyrightDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<CopyrightDTO> partialUpdate(CopyrightDTO copyrightDTO) {
        log.debug("Request to partially update Copyright : {}", copyrightDTO);

        return copyrightRepository
            .findById(copyrightDTO.getId())
            .map(existingCopyright -> {
                copyrightMapper.partialUpdate(existingCopyright, copyrightDTO);

                return existingCopyright;
            })
            .flatMap(copyrightRepository::save)
            .map(copyrightMapper::toDto);
    }

    /**
     * Get all the copyrights.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<CopyrightDTO> findAll() {
        log.debug("Request to get all Copyrights");
        return copyrightRepository.findAll().map(copyrightMapper::toDto);
    }

    /**
     *  Get all the copyrights where Video is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<CopyrightDTO> findAllWhereVideoIsNull() {
        log.debug("Request to get all copyrights where Video is null");
        return copyrightRepository.findAllWhereVideoIsNull().map(copyrightMapper::toDto);
    }

    /**
     * Returns the number of copyrights available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return copyrightRepository.count();
    }

    /**
     * Get one copyright by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<CopyrightDTO> findOne(Long id) {
        log.debug("Request to get Copyright : {}", id);
        return copyrightRepository.findById(id).map(copyrightMapper::toDto);
    }

    /**
     * Delete the copyright by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete Copyright : {}", id);
        return copyrightRepository.deleteById(id);
    }
}
