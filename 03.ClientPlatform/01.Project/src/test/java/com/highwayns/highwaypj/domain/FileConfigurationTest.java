package com.highwayns.highwaypj.domain;

import static com.highwayns.highwaypj.domain.FileConfigurationTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.highwayns.highwaypj.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FileConfigurationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FileConfiguration.class);
        FileConfiguration fileConfiguration1 = getFileConfigurationSample1();
        FileConfiguration fileConfiguration2 = new FileConfiguration();
        assertThat(fileConfiguration1).isNotEqualTo(fileConfiguration2);

        fileConfiguration2.setId(fileConfiguration1.getId());
        assertThat(fileConfiguration1).isEqualTo(fileConfiguration2);

        fileConfiguration2 = getFileConfigurationSample2();
        assertThat(fileConfiguration1).isNotEqualTo(fileConfiguration2);
    }
}
