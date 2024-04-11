package com.highwayac.com.domain;

import static com.highwayac.com.domain.ExtraInfoTestSamples.*;
import static com.highwayac.com.domain.MaterialTestSamples.*;
import static com.highwayac.com.domain.WorkspaceTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.highwayac.com.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MaterialTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Material.class);
        Material material1 = getMaterialSample1();
        Material material2 = new Material();
        assertThat(material1).isNotEqualTo(material2);

        material2.setId(material1.getId());
        assertThat(material1).isEqualTo(material2);

        material2 = getMaterialSample2();
        assertThat(material1).isNotEqualTo(material2);
    }

    @Test
    void extraInfoTest() throws Exception {
        Material material = getMaterialRandomSampleGenerator();
        ExtraInfo extraInfoBack = getExtraInfoRandomSampleGenerator();

        material.setExtraInfo(extraInfoBack);
        assertThat(material.getExtraInfo()).isEqualTo(extraInfoBack);

        material.extraInfo(null);
        assertThat(material.getExtraInfo()).isNull();
    }

    @Test
    void workspaceTest() throws Exception {
        Material material = getMaterialRandomSampleGenerator();
        Workspace workspaceBack = getWorkspaceRandomSampleGenerator();

        material.setWorkspace(workspaceBack);
        assertThat(material.getWorkspace()).isEqualTo(workspaceBack);

        material.workspace(null);
        assertThat(material.getWorkspace()).isNull();
    }
}
