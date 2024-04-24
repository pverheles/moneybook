package com.moneybook.mapper;

import com.moneybook.constants.State;
import com.moneybook.dto.CategoryCreationDto;
import com.moneybook.dto.CategoryReadDto;
import com.moneybook.entity.Category;
import com.moneybook.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category mapCategoryCreationDtoToEntity(CategoryCreationDto categoryCreationDto, User user) {
        Category category = new Category();
        category.setName(categoryCreationDto.getName());
        category.setComment(categoryCreationDto.getComment());
        category.setUser(user);
        category.setState(State.A);

        return category;
    }

    public CategoryReadDto mapEntityToCategoryReadDto(Category category) {
        CategoryReadDto categoryReadDto = new CategoryReadDto();
        categoryReadDto.setId(category.getId());
        categoryReadDto.setState(category.getState());
        categoryReadDto.setName(category.getName());
        categoryReadDto.setComment(category.getComment());
        return categoryReadDto;
    }
}
