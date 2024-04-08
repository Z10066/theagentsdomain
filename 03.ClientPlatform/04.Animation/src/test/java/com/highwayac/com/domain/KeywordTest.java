package com.highwayac.com.domain;

import static com.highwayac.com.domain.KeywordTestSamples.*;
import static com.highwayac.com.domain.VideoTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.highwayac.com.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class KeywordTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Keyword.class);
        Keyword keyword1 = getKeywordSample1();
        Keyword keyword2 = new Keyword();
        assertThat(keyword1).isNotEqualTo(keyword2);

        keyword2.setId(keyword1.getId());
        assertThat(keyword1).isEqualTo(keyword2);

        keyword2 = getKeywordSample2();
        assertThat(keyword1).isNotEqualTo(keyword2);
    }

    @Test
    void videoTest() throws Exception {
        Keyword keyword = getKeywordRandomSampleGenerator();
        Video videoBack = getVideoRandomSampleGenerator();

        keyword.addVideo(videoBack);
        assertThat(keyword.getVideos()).containsOnly(videoBack);
        assertThat(videoBack.getKeywords()).containsOnly(keyword);

        keyword.removeVideo(videoBack);
        assertThat(keyword.getVideos()).doesNotContain(videoBack);
        assertThat(videoBack.getKeywords()).doesNotContain(keyword);

        keyword.videos(new HashSet<>(Set.of(videoBack)));
        assertThat(keyword.getVideos()).containsOnly(videoBack);
        assertThat(videoBack.getKeywords()).containsOnly(keyword);

        keyword.setVideos(new HashSet<>());
        assertThat(keyword.getVideos()).doesNotContain(videoBack);
        assertThat(videoBack.getKeywords()).doesNotContain(keyword);
    }
}
