package com.petrov.service;

import com.petrov.controller.dto.ProductDto;
import com.petrov.entity.Category;
import com.petrov.entity.Product;
import com.petrov.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    ProductService productService;

    ProductRepository productRepository;

    @BeforeEach
    public void init() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductService(productRepository);
    }

    @Test
    public void testFindAll() {
        Category expectedCategory = new Category();
        expectedCategory.setId(1L);
        expectedCategory.setTitle("Category name");

        Product expectedProduct1 = new Product();
        Product expectedProduct2 = new Product();

        expectedProduct1.setId(1L);
        expectedProduct1.setTitle("Product name");
        expectedProduct1.setCategory(expectedCategory);

        expectedProduct2.setId(2L);
        expectedProduct2.setTitle("Product name");
        expectedProduct2.setCategory(expectedCategory);

        List<Product> productList = new ArrayList<>();
        productList.add(expectedProduct1);
        productList.add(expectedProduct2);

        when(productRepository.findAll()).thenReturn(productList);

        List<ProductDto> productDtos = productService.findAll();

        assertEquals(productDtos.size(), productList.size());
        assertNotNull(productDtos.get(0));
        assertEquals(productDtos.get(0).getTitle(), productList.get(0).getTitle());
        assertEquals(productDtos.get(1).getTitle(), productList.get(1).getTitle());
        assertEquals(productDtos.get(0).getCode(), productList.get(0).getCode());
        assertEquals(productDtos.get(1).getCode(), productList.get(1).getCode());
        assertEquals(productDtos.get(0).getPrice(), productList.get(0).getPrice());
        assertEquals(productDtos.get(1).getPrice(), productList.get(1).getPrice());
    }
}
