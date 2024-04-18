package com.moneybook.mapper;

import com.moneybook.constants.State;
import com.moneybook.dto.CategoryCreationDto;
import com.moneybook.entity.Category;
import com.moneybook.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    CategoryMapper categoryMapper;

    @BeforeEach
    void setUp() {
        categoryMapper = new CategoryMapper();
    }

    @Test
    void mapCategoryCreationDtoToEntity_shouldMapCorrectly() {
        CategoryCreationDto categoryCreationDto = new CategoryCreationDto();
        categoryCreationDto.setName("my category");
        categoryCreationDto.setComment("my comment");

        User user = new User();
        user.setEmail("petro@mail.com");

        Category category = categoryMapper.mapCategoryCreationDtoToEntity(categoryCreationDto, user);

        assertThat(category.getName()).isEqualTo("my category");
        assertThat(category.getComment()).isEqualTo("my comment");
        assertThat(category.getState()).isEqualTo(State.A);
        assertThat(category.getUser()).isSameAs(user);
    }

}