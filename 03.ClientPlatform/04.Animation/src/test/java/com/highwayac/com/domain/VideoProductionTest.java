package com.highwayac.com.domain;

import static com.highwayac.com.domain.ExtraInfoTestSamples.*;
import static com.highwayac.com.domain.VideoProductionTestSamples.*;
import static com.highwayac.com.domain.WorkspaceTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.highwayac.com.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class VideoProductionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VideoProduction.class);
        VideoProduction videoProduction1 = getVideoProductionSample1();
        VideoProduction videoProduction2 = new VideoProduction();
        assertThat(videoProduction1).isNotEqualTo(videoProduction2);

        videoProduction2.setId(videoProduction1.getId());
        assertThat(videoProduction1).isEqualTo(videoProduction2);

        videoProduction2 = getVideoProductionSample2();
        assertThat(videoProduction1).isNotEqualTo(videoProduction2);
    }

    @Test
    void extraInfoTest() throws Exception {
        VideoProduction videoProduction = getVideoProductionRandomSampleGenerator();
        ExtraInfo extraInfoBack = getExtraInfoRandomSampleGenerator();

        videoProduction.setExtraInfo(extraInfoBack);
        assertThat(videoProduction.getExtraInfo()).isEqualTo(extraInfoBack);

        videoProduction.extraInfo(null);
        assertThat(videoProduction.getExtraInfo()).isNull();
    }

    @Test
    void workspaceTest() throws Exception {
        VideoProduction videoProduction = getVideoProductionRandomSampleGenerator();
        Workspace workspaceBack = getWorkspaceRandomSampleGenerator();

        videoProduction.setWorkspace(workspaceBack);
        assertThat(videoProduction.getWorkspace()).isEqualTo(workspaceBack);

        videoProduction.workspace(null);
        assertThat(videoProduction.getWorkspace()).isNull();
    }
}
