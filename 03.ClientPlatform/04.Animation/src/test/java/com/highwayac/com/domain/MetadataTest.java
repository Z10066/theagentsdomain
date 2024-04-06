package com.highwayac.com.domain;

import static com.highwayac.com.domain.MetadataTestSamples.*;
import static com.highwayac.com.domain.VideoTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.highwayac.com.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MetadataTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Metadata.class);
        Metadata metadata1 = getMetadataSample1();
        Metadata metadata2 = new Metadata();
        assertThat(metadata1).isNotEqualTo(metadata2);

        metadata2.setId(metadata1.getId());
        assertThat(metadata1).isEqualTo(metadata2);

        metadata2 = getMetadataSample2();
        assertThat(metadata1).isNotEqualTo(metadata2);
    }

    @Test
    void videoTest() throws Exception {
        Metadata metadata = getMetadataRandomSampleGenerator();
        Video videoBack = getVideoRandomSampleGenerator();

        metadata.setVideo(videoBack);
        assertThat(metadata.getVideo()).isEqualTo(videoBack);
        assertThat(videoBack.getExtraInfo()).isEqualTo(metadata);

        metadata.video(null);
        assertThat(metadata.getVideo()).isNull();
        assertThat(videoBack.getExtraInfo()).isNull();
    }
}
