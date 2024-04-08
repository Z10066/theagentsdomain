package com.highwayac.com.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.highwayac.com.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CopyrightDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CopyrightDTO.class);
        CopyrightDTO copyrightDTO1 = new CopyrightDTO();
        copyrightDTO1.setId(1L);
        CopyrightDTO copyrightDTO2 = new CopyrightDTO();
        assertThat(copyrightDTO1).isNotEqualTo(copyrightDTO2);
        copyrightDTO2.setId(copyrightDTO1.getId());
        assertThat(copyrightDTO1).isEqualTo(copyrightDTO2);
        copyrightDTO2.setId(2L);
        assertThat(copyrightDTO1).isNotEqualTo(copyrightDTO2);
        copyrightDTO1.setId(null);
        assertThat(copyrightDTO1).isNotEqualTo(copyrightDTO2);
    }
}
