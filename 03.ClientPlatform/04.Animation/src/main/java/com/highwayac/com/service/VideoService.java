package com.highwayac.com.service;

import com.highwayac.com.repository.VideoRepository;
import com.highwayac.com.service.dto.VideoDTO;
import com.highwayac.com.service.mapper.VideoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link com.highwayac.com.domain.Video}.
 */
@Service
@Transactional
public class VideoService {

    private final Logger log = LoggerFactory.getLogger(VideoService.class);

    private final VideoRepository videoRepository;

    private final VideoMapper videoMapper;

    public VideoService(VideoRepository videoRepository, VideoMapper videoMapper) {
        this.videoRepository = videoRepository;
        this.videoMapper = videoMapper;
    }

    /**
     * Save a video.
     *
     * @param videoDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<VideoDTO> save(VideoDTO videoDTO) {
        log.debug("Request to save Video : {}", videoDTO);
        return videoRepository.save(videoMapper.toEntity(videoDTO)).map(videoMapper::toDto);
    }

    /**
     * Update a video.
     *
     * @param videoDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<VideoDTO> update(VideoDTO videoDTO) {
        log.debug("Request to update Video : {}", videoDTO);
        return videoRepository.save(videoMapper.toEntity(videoDTO)).map(videoMapper::toDto);
    }

    /**
     * Partially update a video.
     *
     * @param videoDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<VideoDTO> partialUpdate(VideoDTO videoDTO) {
        log.debug("Request to partially update Video : {}", videoDTO);

        return videoRepository
            .findById(videoDTO.getId())
            .map(existingVideo -> {
                videoMapper.partialUpdate(existingVideo, videoDTO);

                return existingVideo;
            })
            .flatMap(videoRepository::save)
            .map(videoMapper::toDto);
    }

    /**
     * Get all the videos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<VideoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Videos");
        return videoRepository.findAllBy(pageable).map(videoMapper::toDto);
    }

    /**
     * Get all the videos with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<VideoDTO> findAllWithEagerRelationships(Pageable pageable) {
        return videoRepository.findAllWithEagerRelationships(pageable).map(videoMapper::toDto);
    }

    /**
     * Returns the number of videos available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return videoRepository.count();
    }

    /**
     * Get one video by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<VideoDTO> findOne(Long id) {
        log.debug("Request to get Video : {}", id);
        return videoRepository.findOneWithEagerRelationships(id).map(videoMapper::toDto);
    }

    /**
     * Delete the video by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete Video : {}", id);
        return videoRepository.deleteById(id);
    }
}
