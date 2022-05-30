package com.petrov.controller;

import com.petrov.controller.dto.CategoryDto;
import com.petrov.controller.dto.ProductDto;
import com.petrov.entity.Category;
import com.petrov.entity.Product;
import com.petrov.repository.CategoryRepository;
import com.petrov.repository.ProductRepository;
import com.petrov.service.CategoryService;
import com.petrov.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private ProductService productService;

    @Test
    public void testProduct() throws Exception {
        CategoryDto categoryDto = new CategoryDto(1L, "Category");
        categoryService.saveOrUpdate(categoryDto);
        ProductDto productDto = new ProductDto(1L, "12345", "Apple","www.product.com", categoryDto, new BigDecimal(1234));
        productService.saveOrUpdate(productDto);

        mvc.perform(MockMvcRequestBuilders
                .get("/products")
                .param("categoryId", categoryDto.getId().toString())
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("products"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attribute("products", productService.findAll()));
    }
}
