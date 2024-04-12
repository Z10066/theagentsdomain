package com.highwayac.com.domain;

import static com.highwayac.com.domain.ExtraInfoTestSamples.*;
import static com.highwayac.com.domain.HistoryTestSamples.*;
import static com.highwayac.com.domain.MaterialTestSamples.*;
import static com.highwayac.com.domain.VideoProductionTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.highwayac.com.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ExtraInfoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExtraInfo.class);
        ExtraInfo extraInfo1 = getExtraInfoSample1();
        ExtraInfo extraInfo2 = new ExtraInfo();
        assertThat(extraInfo1).isNotEqualTo(extraInfo2);

        extraInfo2.setId(extraInfo1.getId());
        assertThat(extraInfo1).isEqualTo(extraInfo2);

        extraInfo2 = getExtraInfoSample2();
        assertThat(extraInfo1).isNotEqualTo(extraInfo2);
    }

    @Test
    void videoProductionTest() throws Exception {
        ExtraInfo extraInfo = getExtraInfoRandomSampleGenerator();
        VideoProduction videoProductionBack = getVideoProductionRandomSampleGenerator();

        extraInfo.setVideoProduction(videoProductionBack);
        assertThat(extraInfo.getVideoProduction()).isEqualTo(videoProductionBack);
        assertThat(videoProductionBack.getExtraInfo()).isEqualTo(extraInfo);

        extraInfo.videoProduction(null);
        assertThat(extraInfo.getVideoProduction()).isNull();
        assertThat(videoProductionBack.getExtraInfo()).isNull();
    }

    @Test
    void materialTest() throws Exception {
        ExtraInfo extraInfo = getExtraInfoRandomSampleGenerator();
        Material materialBack = getMaterialRandomSampleGenerator();

        extraInfo.setMaterial(materialBack);
        assertThat(extraInfo.getMaterial()).isEqualTo(materialBack);
        assertThat(materialBack.getExtraInfo()).isEqualTo(extraInfo);

        extraInfo.material(null);
        assertThat(extraInfo.getMaterial()).isNull();
        assertThat(materialBack.getExtraInfo()).isNull();
    }

    @Test
    void historyTest() throws Exception {
        ExtraInfo extraInfo = getExtraInfoRandomSampleGenerator();
        History historyBack = getHistoryRandomSampleGenerator();

        extraInfo.setHistory(historyBack);
        assertThat(extraInfo.getHistory()).isEqualTo(historyBack);
        assertThat(historyBack.getExtraInfo()).isEqualTo(extraInfo);

        extraInfo.history(null);
        assertThat(extraInfo.getHistory()).isNull();
        assertThat(historyBack.getExtraInfo()).isNull();
    }
}
