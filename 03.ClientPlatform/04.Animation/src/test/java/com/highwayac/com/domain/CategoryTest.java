package com.highwayac.com.domain;

import static com.highwayac.com.domain.CategoryTestSamples.*;
import static com.highwayac.com.domain.VideoTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.highwayac.com.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CategoryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Category.class);
        Category category1 = getCategorySample1();
        Category category2 = new Category();
        assertThat(category1).isNotEqualTo(category2);

        category2.setId(category1.getId());
        assertThat(category1).isEqualTo(category2);

        category2 = getCategorySample2();
        assertThat(category1).isNotEqualTo(category2);
    }

    @Test
    void videoTest() throws Exception {
        Category category = getCategoryRandomSampleGenerator();
        Video videoBack = getVideoRandomSampleGenerator();

        category.setVideo(videoBack);
        assertThat(category.getVideo()).isEqualTo(videoBack);
        assertThat(videoBack.getCategory()).isEqualTo(category);

        category.video(null);
        assertThat(category.getVideo()).isNull();
        assertThat(videoBack.getCategory()).isNull();
    }
}
