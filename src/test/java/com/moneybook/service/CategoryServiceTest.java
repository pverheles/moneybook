package com.moneybook.service;

import com.moneybook.dto.AccountReadDto;
import com.moneybook.dto.CategoryCreationDto;
import com.moneybook.dto.CategoryReadDto;
import com.moneybook.entity.Account;
import com.moneybook.entity.Category;
import com.moneybook.entity.User;
import com.moneybook.mapper.CategoryMapper;
import com.moneybook.repository.CategoryRepository;
import com.moneybook.repository.UserRepository;
import com.moneybook.usercontext.UserContext;
import com.moneybook.usercontext.UserContextService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    CategoryMapper categoryMapper;

    @Mock
    UserRepository userRepository;

    @Mock
    UserContextService userContextService;

    @BeforeEach
    void setUp() {
        categoryService = new CategoryService(categoryRepository, categoryMapper, userRepository, userContextService);
    }

    @Test
    void createCategory_shouldCreateCategoryForUserInContext() {
        UserContext userContext = new UserContext("petro@mail.com");
        User user = new User();
        user.setEmail("petro@mail.com");

        when(userContextService.getUserContext()).thenReturn(userContext);
        when(userRepository.findByEmail("petro@mail.com")).thenReturn(Optional.of(user));

        CategoryCreationDto categoryCreationDto = new CategoryCreationDto();
        categoryCreationDto.setName("Food");
        categoryCreationDto.setComment("all food");

        Category category = new Category();
        when(categoryMapper.mapCategoryCreationDtoToEntity(categoryCreationDto, user)).thenReturn(category);

        categoryService.createCategory(categoryCreationDto);

        verify(categoryRepository).save(category);
    }

    @Test
    void getCategories_shouldReturnListOfCategoriesOfUserInContext() {
        UserContext userContext = new UserContext("petro@mail.com");

        List<Category> categories = new ArrayList<>();
        Category category = new Category();
        category.setName("test");
        categories.add(category);

        CategoryReadDto categoryReadDto = new CategoryReadDto();
        categoryReadDto.setName("test");

        when(userContextService.getUserContext()).thenReturn(userContext);
        when(categoryRepository.findByUserEmail("petro@mail.com")).thenReturn(categories);
        when(categoryMapper.mapEntityToCategoryReadDto(category)).thenReturn(categoryReadDto);

        List<CategoryReadDto> expectedCategoryDtos = new ArrayList<>();
        expectedCategoryDtos.add(categoryReadDto);

        List<CategoryReadDto> actualAccounts = categoryService.getCategories();

        assertThat(actualAccounts).isEqualTo(expectedCategoryDtos);
    }
}