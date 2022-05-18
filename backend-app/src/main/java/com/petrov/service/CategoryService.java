package com.petrov.service;

import com.petrov.controller.dto.CategoryDto;
import com.petrov.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.petrov.service.Utils.mapCategoryDto;

@Service
public class CategoryService implements Serializable {

    @Autowired
    public CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream().map(Utils::mapCategoryDto).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public CategoryDto findById(Long id) {
        return mapCategoryDto(categoryRepository.findById(id).get());
    }

    public CategoryDto findByTitle(String title) {
        List<CategoryDto> categoryDtoList = categoryRepository.findAll().stream()
                .map(Utils::mapCategoryDto).filter(Objects::nonNull).collect(Collectors.toList());
        for (CategoryDto categoryDto : categoryDtoList) {
            if (title.equals(categoryDto.getTitle())) {
                return categoryDto;
            }
        }
        return null;
    }
}
