package com.highwayac.com.service.mapper;

import com.highwayac.com.domain.Copyright;
import com.highwayac.com.service.dto.CopyrightDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Copyright} and its DTO {@link CopyrightDTO}.
 */
@Mapper(componentModel = "spring")
public interface CopyrightMapper extends EntityMapper<CopyrightDTO, Copyright> {}
