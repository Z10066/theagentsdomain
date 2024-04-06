package com.highwayac.com.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.highwayac.com.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreatorDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreatorDTO.class);
        CreatorDTO creatorDTO1 = new CreatorDTO();
        creatorDTO1.setId(1L);
        CreatorDTO creatorDTO2 = new CreatorDTO();
        assertThat(creatorDTO1).isNotEqualTo(creatorDTO2);
        creatorDTO2.setId(creatorDTO1.getId());
        assertThat(creatorDTO1).isEqualTo(creatorDTO2);
        creatorDTO2.setId(2L);
        assertThat(creatorDTO1).isNotEqualTo(creatorDTO2);
        creatorDTO1.setId(null);
        assertThat(creatorDTO1).isNotEqualTo(creatorDTO2);
    }
}
