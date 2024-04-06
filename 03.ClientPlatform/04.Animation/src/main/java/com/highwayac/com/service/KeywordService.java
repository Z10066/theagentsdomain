package com.highwayac.com.service;

import com.highwayac.com.repository.KeywordRepository;
import com.highwayac.com.service.dto.KeywordDTO;
import com.highwayac.com.service.mapper.KeywordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link com.highwayac.com.domain.Keyword}.
 */
@Service
@Transactional
public class KeywordService {

    private final Logger log = LoggerFactory.getLogger(KeywordService.class);

    private final KeywordRepository keywordRepository;

    private final KeywordMapper keywordMapper;

    public KeywordService(KeywordRepository keywordRepository, KeywordMapper keywordMapper) {
        this.keywordRepository = keywordRepository;
        this.keywordMapper = keywordMapper;
    }

    /**
     * Save a keyword.
     *
     * @param keywordDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<KeywordDTO> save(KeywordDTO keywordDTO) {
        log.debug("Request to save Keyword : {}", keywordDTO);
        return keywordRepository.save(keywordMapper.toEntity(keywordDTO)).map(keywordMapper::toDto);
    }

    /**
     * Update a keyword.
     *
     * @param keywordDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<KeywordDTO> update(KeywordDTO keywordDTO) {
        log.debug("Request to update Keyword : {}", keywordDTO);
        return keywordRepository.save(keywordMapper.toEntity(keywordDTO)).map(keywordMapper::toDto);
    }

    /**
     * Partially update a keyword.
     *
     * @param keywordDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<KeywordDTO> partialUpdate(KeywordDTO keywordDTO) {
        log.debug("Request to partially update Keyword : {}", keywordDTO);

        return keywordRepository
            .findById(keywordDTO.getId())
            .map(existingKeyword -> {
                keywordMapper.partialUpdate(existingKeyword, keywordDTO);

                return existingKeyword;
            })
            .flatMap(keywordRepository::save)
            .map(keywordMapper::toDto);
    }

    /**
     * Get all the keywords.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<KeywordDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Keywords");
        return keywordRepository.findAllBy(pageable).map(keywordMapper::toDto);
    }

    /**
     * Returns the number of keywords available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return keywordRepository.count();
    }

    /**
     * Get one keyword by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<KeywordDTO> findOne(Long id) {
        log.debug("Request to get Keyword : {}", id);
        return keywordRepository.findById(id).map(keywordMapper::toDto);
    }

    /**
     * Delete the keyword by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete Keyword : {}", id);
        return keywordRepository.deleteById(id);
    }
}
