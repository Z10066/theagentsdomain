package com.highwayac.com.domain;

import static com.highwayac.com.domain.HistoryTestSamples.*;
import static com.highwayac.com.domain.MaterialTestSamples.*;
import static com.highwayac.com.domain.MemberTestSamples.*;
import static com.highwayac.com.domain.VideoProductionTestSamples.*;
import static com.highwayac.com.domain.WorkspaceTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.highwayac.com.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class WorkspaceTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Workspace.class);
        Workspace workspace1 = getWorkspaceSample1();
        Workspace workspace2 = new Workspace();
        assertThat(workspace1).isNotEqualTo(workspace2);

        workspace2.setId(workspace1.getId());
        assertThat(workspace1).isEqualTo(workspace2);

        workspace2 = getWorkspaceSample2();
        assertThat(workspace1).isNotEqualTo(workspace2);
    }

    @Test
    void memberTest() throws Exception {
        Workspace workspace = getWorkspaceRandomSampleGenerator();
        Member memberBack = getMemberRandomSampleGenerator();

        workspace.addMember(memberBack);
        assertThat(workspace.getMembers()).containsOnly(memberBack);
        assertThat(memberBack.getWorkspace()).isEqualTo(workspace);

        workspace.removeMember(memberBack);
        assertThat(workspace.getMembers()).doesNotContain(memberBack);
        assertThat(memberBack.getWorkspace()).isNull();

        workspace.members(new HashSet<>(Set.of(memberBack)));
        assertThat(workspace.getMembers()).containsOnly(memberBack);
        assertThat(memberBack.getWorkspace()).isEqualTo(workspace);

        workspace.setMembers(new HashSet<>());
        assertThat(workspace.getMembers()).doesNotContain(memberBack);
        assertThat(memberBack.getWorkspace()).isNull();
    }

    @Test
    void videoProductionTest() throws Exception {
        Workspace workspace = getWorkspaceRandomSampleGenerator();
        VideoProduction videoProductionBack = getVideoProductionRandomSampleGenerator();

        workspace.addVideoProduction(videoProductionBack);
        assertThat(workspace.getVideoProductions()).containsOnly(videoProductionBack);
        assertThat(videoProductionBack.getWorkspace()).isEqualTo(workspace);

        workspace.removeVideoProduction(videoProductionBack);
        assertThat(workspace.getVideoProductions()).doesNotContain(videoProductionBack);
        assertThat(videoProductionBack.getWorkspace()).isNull();

        workspace.videoProductions(new HashSet<>(Set.of(videoProductionBack)));
        assertThat(workspace.getVideoProductions()).containsOnly(videoProductionBack);
        assertThat(videoProductionBack.getWorkspace()).isEqualTo(workspace);

        workspace.setVideoProductions(new HashSet<>());
        assertThat(workspace.getVideoProductions()).doesNotContain(videoProductionBack);
        assertThat(videoProductionBack.getWorkspace()).isNull();
    }

    @Test
    void materialTest() throws Exception {
        Workspace workspace = getWorkspaceRandomSampleGenerator();
        Material materialBack = getMaterialRandomSampleGenerator();

        workspace.addMaterial(materialBack);
        assertThat(workspace.getMaterials()).containsOnly(materialBack);
        assertThat(materialBack.getWorkspace()).isEqualTo(workspace);

        workspace.removeMaterial(materialBack);
        assertThat(workspace.getMaterials()).doesNotContain(materialBack);
        assertThat(materialBack.getWorkspace()).isNull();

        workspace.materials(new HashSet<>(Set.of(materialBack)));
        assertThat(workspace.getMaterials()).containsOnly(materialBack);
        assertThat(materialBack.getWorkspace()).isEqualTo(workspace);

        workspace.setMaterials(new HashSet<>());
        assertThat(workspace.getMaterials()).doesNotContain(materialBack);
        assertThat(materialBack.getWorkspace()).isNull();
    }

    @Test
    void historyTest() throws Exception {
        Workspace workspace = getWorkspaceRandomSampleGenerator();
        History historyBack = getHistoryRandomSampleGenerator();

        workspace.addHistory(historyBack);
        assertThat(workspace.getHistories()).containsOnly(historyBack);
        assertThat(historyBack.getWorkspace()).isEqualTo(workspace);

        workspace.removeHistory(historyBack);
        assertThat(workspace.getHistories()).doesNotContain(historyBack);
        assertThat(historyBack.getWorkspace()).isNull();

        workspace.histories(new HashSet<>(Set.of(historyBack)));
        assertThat(workspace.getHistories()).containsOnly(historyBack);
        assertThat(historyBack.getWorkspace()).isEqualTo(workspace);

        workspace.setHistories(new HashSet<>());
        assertThat(workspace.getHistories()).doesNotContain(historyBack);
        assertThat(historyBack.getWorkspace()).isNull();
    }
}
