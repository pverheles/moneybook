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
import com.moneybook.usercontext.UserContextService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final UserRepository userRepository;
    private final UserContextService userContextService;

    public CategoryService(CategoryRepository categoryRepository,
                           CategoryMapper categoryMapper,
                           UserRepository userRepository,
                           UserContextService userContextService) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.userRepository = userRepository;
        this.userContextService = userContextService;
    }

    public void createCategory(CategoryCreationDto categoryCreationDto) {
        String email = userContextService.getUserContext().getEmail();
        User user = userRepository.findByEmail(email).orElseThrow();
        Category category = categoryMapper.mapCategoryCreationDtoToEntity(categoryCreationDto, user);
        categoryRepository.save(category);
    }

    public List<CategoryReadDto> getCategories() {
        String email = userContextService.getUserContext().getEmail();
        List<Category> accounts = categoryRepository.findByUserEmail(email);
        return accounts.stream().map(categoryMapper::mapEntityToCategoryReadDto).toList();
    }}
