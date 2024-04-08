package com.highwayac.com.service;

import com.highwayac.com.repository.MetadataRepository;
import com.highwayac.com.service.dto.MetadataDTO;
import com.highwayac.com.service.mapper.MetadataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link com.highwayac.com.domain.Metadata}.
 */
@Service
@Transactional
public class MetadataService {

    private final Logger log = LoggerFactory.getLogger(MetadataService.class);

    private final MetadataRepository metadataRepository;

    private final MetadataMapper metadataMapper;

    public MetadataService(MetadataRepository metadataRepository, MetadataMapper metadataMapper) {
        this.metadataRepository = metadataRepository;
        this.metadataMapper = metadataMapper;
    }

    /**
     * Save a metadata.
     *
     * @param metadataDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<MetadataDTO> save(MetadataDTO metadataDTO) {
        log.debug("Request to save Metadata : {}", metadataDTO);
        return metadataRepository.save(metadataMapper.toEntity(metadataDTO)).map(metadataMapper::toDto);
    }

    /**
     * Update a metadata.
     *
     * @param metadataDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<MetadataDTO> update(MetadataDTO metadataDTO) {
        log.debug("Request to update Metadata : {}", metadataDTO);
        return metadataRepository.save(metadataMapper.toEntity(metadataDTO)).map(metadataMapper::toDto);
    }

    /**
     * Partially update a metadata.
     *
     * @param metadataDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<MetadataDTO> partialUpdate(MetadataDTO metadataDTO) {
        log.debug("Request to partially update Metadata : {}", metadataDTO);

        return metadataRepository
            .findById(metadataDTO.getId())
            .map(existingMetadata -> {
                metadataMapper.partialUpdate(existingMetadata, metadataDTO);

                return existingMetadata;
            })
            .flatMap(metadataRepository::save)
            .map(metadataMapper::toDto);
    }

    /**
     * Get all the metadata.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<MetadataDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Metadata");
        return metadataRepository.findAllBy(pageable).map(metadataMapper::toDto);
    }

    /**
     *  Get all the metadata where Video is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<MetadataDTO> findAllWhereVideoIsNull() {
        log.debug("Request to get all metadata where Video is null");
        return metadataRepository.findAllWhereVideoIsNull().map(metadataMapper::toDto);
    }

    /**
     * Returns the number of metadata available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return metadataRepository.count();
    }

    /**
     * Get one metadata by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<MetadataDTO> findOne(Long id) {
        log.debug("Request to get Metadata : {}", id);
        return metadataRepository.findById(id).map(metadataMapper::toDto);
    }

    /**
     * Delete the metadata by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete Metadata : {}", id);
        return metadataRepository.deleteById(id);
    }
}
