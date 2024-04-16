package com.highwayac.com.domain;

import static com.highwayac.com.domain.VideoHintTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.highwayac.com.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class VideoHintTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VideoHint.class);
        VideoHint videoHint1 = getVideoHintSample1();
        VideoHint videoHint2 = new VideoHint();
        assertThat(videoHint1).isNotEqualTo(videoHint2);

        videoHint2.setId(videoHint1.getId());
        assertThat(videoHint1).isEqualTo(videoHint2);

        videoHint2 = getVideoHintSample2();
        assertThat(videoHint1).isNotEqualTo(videoHint2);
    }
}
