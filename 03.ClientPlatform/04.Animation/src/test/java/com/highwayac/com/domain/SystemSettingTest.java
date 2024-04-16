package com.highwayac.com.domain;

import static com.highwayac.com.domain.SystemSettingTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.highwayac.com.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SystemSettingTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SystemSetting.class);
        SystemSetting systemSetting1 = getSystemSettingSample1();
        SystemSetting systemSetting2 = new SystemSetting();
        assertThat(systemSetting1).isNotEqualTo(systemSetting2);

        systemSetting2.setId(systemSetting1.getId());
        assertThat(systemSetting1).isEqualTo(systemSetting2);

        systemSetting2 = getSystemSettingSample2();
        assertThat(systemSetting1).isNotEqualTo(systemSetting2);
    }
}
