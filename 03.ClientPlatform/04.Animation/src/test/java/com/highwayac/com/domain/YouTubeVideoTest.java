package com.highwayac.com.domain;

import static com.highwayac.com.domain.YouTubeVideoTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.highwayac.com.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class YouTubeVideoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(YouTubeVideo.class);
        YouTubeVideo youTubeVideo1 = getYouTubeVideoSample1();
        YouTubeVideo youTubeVideo2 = new YouTubeVideo();
        assertThat(youTubeVideo1).isNotEqualTo(youTubeVideo2);

        youTubeVideo2.setId(youTubeVideo1.getId());
        assertThat(youTubeVideo1).isEqualTo(youTubeVideo2);

        youTubeVideo2 = getYouTubeVideoSample2();
        assertThat(youTubeVideo1).isNotEqualTo(youTubeVideo2);
    }
}
