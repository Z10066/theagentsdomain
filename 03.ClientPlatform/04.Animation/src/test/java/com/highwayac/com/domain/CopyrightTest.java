package com.highwayac.com.domain;

import static com.highwayac.com.domain.CopyrightTestSamples.*;
import static com.highwayac.com.domain.VideoTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.highwayac.com.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CopyrightTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Copyright.class);
        Copyright copyright1 = getCopyrightSample1();
        Copyright copyright2 = new Copyright();
        assertThat(copyright1).isNotEqualTo(copyright2);

        copyright2.setId(copyright1.getId());
        assertThat(copyright1).isEqualTo(copyright2);

        copyright2 = getCopyrightSample2();
        assertThat(copyright1).isNotEqualTo(copyright2);
    }

    @Test
    void videoTest() throws Exception {
        Copyright copyright = getCopyrightRandomSampleGenerator();
        Video videoBack = getVideoRandomSampleGenerator();

        copyright.setVideo(videoBack);
        assertThat(copyright.getVideo()).isEqualTo(videoBack);
        assertThat(videoBack.getCopyright()).isEqualTo(copyright);

        copyright.video(null);
        assertThat(copyright.getVideo()).isNull();
        assertThat(videoBack.getCopyright()).isNull();
    }
}
