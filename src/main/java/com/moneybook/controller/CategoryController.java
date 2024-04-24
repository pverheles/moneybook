package com.moneybook.controller;

import com.moneybook.dto.*;
import com.moneybook.service.CategoryService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @Parameter(in = ParameterIn.HEADER,name = "email", required = true, content = @Content(schema = @Schema(type = "string")))
    public void createCategory(@RequestBody @Valid CategoryCreationDto categoryCreationDto) {
        categoryService.createCategory(categoryCreationDto);
    }

    @GetMapping
    @Parameter(in = ParameterIn.HEADER,name = "email", required = true, content = @Content(schema = @Schema(type = "string")))
    public List<CategoryReadDto> getCategories() {
        return categoryService.getCategories();
    }
}
