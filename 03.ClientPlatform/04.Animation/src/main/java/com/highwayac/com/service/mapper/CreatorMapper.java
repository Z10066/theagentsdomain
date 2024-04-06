package com.highwayac.com.service.mapper;

import com.highwayac.com.domain.Creator;
import com.highwayac.com.service.dto.CreatorDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Creator} and its DTO {@link CreatorDTO}.
 */
@Mapper(componentModel = "spring")
public interface CreatorMapper extends EntityMapper<CreatorDTO, Creator> {}
