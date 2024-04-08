package com.highwayac.com.service.mapper;

import com.highwayac.com.domain.Category;
import com.highwayac.com.domain.Copyright;
import com.highwayac.com.domain.Creator;
import com.highwayac.com.domain.Keyword;
import com.highwayac.com.domain.Metadata;
import com.highwayac.com.domain.Video;
import com.highwayac.com.service.dto.CategoryDTO;
import com.highwayac.com.service.dto.CopyrightDTO;
import com.highwayac.com.service.dto.CreatorDTO;
import com.highwayac.com.service.dto.KeywordDTO;
import com.highwayac.com.service.dto.MetadataDTO;
import com.highwayac.com.service.dto.VideoDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Video} and its DTO {@link VideoDTO}.
 */
@Mapper(componentModel = "spring")
public interface VideoMapper extends EntityMapper<VideoDTO, Video> {
    @Mapping(target = "creator", source = "creator", qualifiedByName = "creatorId")
    @Mapping(target = "category", source = "category", qualifiedByName = "categoryId")
    @Mapping(target = "copyright", source = "copyright", qualifiedByName = "copyrightId")
    @Mapping(target = "extraInfo", source = "extraInfo", qualifiedByName = "metadataId")
    @Mapping(target = "keywords", source = "keywords", qualifiedByName = "keywordIdSet")
    VideoDTO toDto(Video s);

    @Mapping(target = "removeKeyword", ignore = true)
    Video toEntity(VideoDTO videoDTO);

    @Named("creatorId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CreatorDTO toDtoCreatorId(Creator creator);

    @Named("categoryId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CategoryDTO toDtoCategoryId(Category category);

    @Named("copyrightId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CopyrightDTO toDtoCopyrightId(Copyright copyright);

    @Named("metadataId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    MetadataDTO toDtoMetadataId(Metadata metadata);

    @Named("keywordId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    KeywordDTO toDtoKeywordId(Keyword keyword);

    @Named("keywordIdSet")
    default Set<KeywordDTO> toDtoKeywordIdSet(Set<Keyword> keyword) {
        return keyword.stream().map(this::toDtoKeywordId).collect(Collectors.toSet());
    }
}
