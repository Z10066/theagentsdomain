package com.highwayac.com.domain;

import static com.highwayac.com.domain.CreatorTestSamples.*;
import static com.highwayac.com.domain.VideoTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.highwayac.com.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreatorTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Creator.class);
        Creator creator1 = getCreatorSample1();
        Creator creator2 = new Creator();
        assertThat(creator1).isNotEqualTo(creator2);

        creator2.setId(creator1.getId());
        assertThat(creator1).isEqualTo(creator2);

        creator2 = getCreatorSample2();
        assertThat(creator1).isNotEqualTo(creator2);
    }

    @Test
    void videoTest() throws Exception {
        Creator creator = getCreatorRandomSampleGenerator();
        Video videoBack = getVideoRandomSampleGenerator();

        creator.setVideo(videoBack);
        assertThat(creator.getVideo()).isEqualTo(videoBack);
        assertThat(videoBack.getCreator()).isEqualTo(creator);

        creator.video(null);
        assertThat(creator.getVideo()).isNull();
        assertThat(videoBack.getCreator()).isNull();
    }
}
