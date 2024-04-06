package com.highwayac.com.service.mapper;

import com.highwayac.com.domain.Keyword;
import com.highwayac.com.service.dto.KeywordDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Keyword} and its DTO {@link KeywordDTO}.
 */
@Mapper(componentModel = "spring")
public interface KeywordMapper extends EntityMapper<KeywordDTO, Keyword> {}
