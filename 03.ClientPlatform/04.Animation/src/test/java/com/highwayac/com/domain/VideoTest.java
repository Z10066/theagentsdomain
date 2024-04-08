package com.highwayac.com.domain;

import static com.highwayac.com.domain.CategoryTestSamples.*;
import static com.highwayac.com.domain.CopyrightTestSamples.*;
import static com.highwayac.com.domain.CreatorTestSamples.*;
import static com.highwayac.com.domain.KeywordTestSamples.*;
import static com.highwayac.com.domain.MetadataTestSamples.*;
import static com.highwayac.com.domain.VideoTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.highwayac.com.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class VideoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Video.class);
        Video video1 = getVideoSample1();
        Video video2 = new Video();
        assertThat(video1).isNotEqualTo(video2);

        video2.setId(video1.getId());
        assertThat(video1).isEqualTo(video2);

        video2 = getVideoSample2();
        assertThat(video1).isNotEqualTo(video2);
    }

    @Test
    void creatorTest() throws Exception {
        Video video = getVideoRandomSampleGenerator();
        Creator creatorBack = getCreatorRandomSampleGenerator();

        video.setCreator(creatorBack);
        assertThat(video.getCreator()).isEqualTo(creatorBack);

        video.creator(null);
        assertThat(video.getCreator()).isNull();
    }

    @Test
    void categoryTest() throws Exception {
        Video video = getVideoRandomSampleGenerator();
        Category categoryBack = getCategoryRandomSampleGenerator();

        video.setCategory(categoryBack);
        assertThat(video.getCategory()).isEqualTo(categoryBack);

        video.category(null);
        assertThat(video.getCategory()).isNull();
    }

    @Test
    void copyrightTest() throws Exception {
        Video video = getVideoRandomSampleGenerator();
        Copyright copyrightBack = getCopyrightRandomSampleGenerator();

        video.setCopyright(copyrightBack);
        assertThat(video.getCopyright()).isEqualTo(copyrightBack);

        video.copyright(null);
        assertThat(video.getCopyright()).isNull();
    }

    @Test
    void extraInfoTest() throws Exception {
        Video video = getVideoRandomSampleGenerator();
        Metadata metadataBack = getMetadataRandomSampleGenerator();

        video.setExtraInfo(metadataBack);
        assertThat(video.getExtraInfo()).isEqualTo(metadataBack);

        video.extraInfo(null);
        assertThat(video.getExtraInfo()).isNull();
    }

    @Test
    void keywordTest() throws Exception {
        Video video = getVideoRandomSampleGenerator();
        Keyword keywordBack = getKeywordRandomSampleGenerator();

        video.addKeyword(keywordBack);
        assertThat(video.getKeywords()).containsOnly(keywordBack);

        video.removeKeyword(keywordBack);
        assertThat(video.getKeywords()).doesNotContain(keywordBack);

        video.keywords(new HashSet<>(Set.of(keywordBack)));
        assertThat(video.getKeywords()).containsOnly(keywordBack);

        video.setKeywords(new HashSet<>());
        assertThat(video.getKeywords()).doesNotContain(keywordBack);
    }
}
