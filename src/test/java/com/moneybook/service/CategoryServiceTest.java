package com.moneybook.service;

import com.moneybook.dto.CategoryCreationDto;
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

import java.util.Optional;

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
}