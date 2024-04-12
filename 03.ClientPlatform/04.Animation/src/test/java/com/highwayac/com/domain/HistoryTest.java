package com.highwayac.com.domain;

import static com.highwayac.com.domain.ExtraInfoTestSamples.*;
import static com.highwayac.com.domain.HistoryTestSamples.*;
import static com.highwayac.com.domain.WorkspaceTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.highwayac.com.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HistoryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(History.class);
        History history1 = getHistorySample1();
        History history2 = new History();
        assertThat(history1).isNotEqualTo(history2);

        history2.setId(history1.getId());
        assertThat(history1).isEqualTo(history2);

        history2 = getHistorySample2();
        assertThat(history1).isNotEqualTo(history2);
    }

    @Test
    void extraInfoTest() throws Exception {
        History history = getHistoryRandomSampleGenerator();
        ExtraInfo extraInfoBack = getExtraInfoRandomSampleGenerator();

        history.setExtraInfo(extraInfoBack);
        assertThat(history.getExtraInfo()).isEqualTo(extraInfoBack);

        history.extraInfo(null);
        assertThat(history.getExtraInfo()).isNull();
    }

    @Test
    void workspaceTest() throws Exception {
        History history = getHistoryRandomSampleGenerator();
        Workspace workspaceBack = getWorkspaceRandomSampleGenerator();

        history.setWorkspace(workspaceBack);
        assertThat(history.getWorkspace()).isEqualTo(workspaceBack);

        history.workspace(null);
        assertThat(history.getWorkspace()).isNull();
    }
}
