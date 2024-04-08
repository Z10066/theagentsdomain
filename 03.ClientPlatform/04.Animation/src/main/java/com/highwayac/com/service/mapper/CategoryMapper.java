package com.highwayac.com.service.mapper;

import com.highwayac.com.domain.Category;
import com.highwayac.com.service.dto.CategoryDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Category} and its DTO {@link CategoryDTO}.
 */
@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category> {}
