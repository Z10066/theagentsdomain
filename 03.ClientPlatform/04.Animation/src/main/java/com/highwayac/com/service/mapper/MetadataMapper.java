package com.highwayac.com.service.mapper;

import com.highwayac.com.domain.Metadata;
import com.highwayac.com.service.dto.MetadataDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Metadata} and its DTO {@link MetadataDTO}.
 */
@Mapper(componentModel = "spring")
public interface MetadataMapper extends EntityMapper<MetadataDTO, Metadata> {}
