package com.petrov.service;

import com.petrov.controller.dto.CategoryDto;
import com.petrov.controller.dto.ProductDto;
import com.petrov.entity.Category;
import com.petrov.entity.Product;

public class Utils {

    public static ProductDto mapProduct(Product product) {
        if (product != null) {
            return new ProductDto(product.getId(),
                    product.getCode(),
                    product.getUrl(),
                    product.getTitle(),
                    mapCategoryDto(product.getCategory()),
                    product.getPrice());
        } else {
            return null;
        }
    }

    public static Product mapProductDto(ProductDto productDto) {
        if (productDto != null) {
            return new Product(
                    productDto.getId(),
                    productDto.getCode(),
                    productDto.getUrl(),
                    productDto.getTitle(),
                    mapCategory(productDto.getCategoryDto()),
                    productDto.getPrice()
            );
        } else {
            return null;
        }
    }

    public static Category mapCategory(CategoryDto categoryDto) {
        if (categoryDto != null) {
            return new Category(categoryDto.getId(), categoryDto.getTitle());
        } else {
            return null;
        }
    }

    public static CategoryDto mapCategoryDto(Category category) {
        if (category != null) {
            return new CategoryDto(category.getId(), category.getTitle());
        } else {
            return null;
        }
    }

}
