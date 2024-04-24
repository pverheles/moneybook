package com.moneybook.mapper;

import com.moneybook.constants.Bank;
import com.moneybook.constants.Currency;
import com.moneybook.constants.State;
import com.moneybook.dto.AccountReadDto;
import com.moneybook.dto.CategoryCreationDto;
import com.moneybook.dto.CategoryReadDto;
import com.moneybook.entity.Account;
import com.moneybook.entity.Category;
import com.moneybook.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

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


    @Test
    void mapEntityToCategoryReadDto_shouldMapCorrectly() {
        Category category = new Category();
        category.setName("test1");
        category.setComment("comment");
        category.setId(3L);
        category.setState(State.A);

        CategoryReadDto categoryReadDto = categoryMapper.mapEntityToCategoryReadDto(category);

        CategoryReadDto expectedCategoryReadDto = new CategoryReadDto();
        expectedCategoryReadDto.setName("test1");
        expectedCategoryReadDto.setComment("comment");
        expectedCategoryReadDto.setId(3L);
        expectedCategoryReadDto.setState(State.A);

        assertThat(categoryReadDto).isEqualTo(expectedCategoryReadDto);

    }

}