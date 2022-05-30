package com.petrov.service;

import com.petrov.controller.dto.ProductDto;
import com.petrov.entity.Product;
import com.petrov.repository.CategoryRepository;
import com.petrov.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.petrov.service.Utils.mapProduct;
import static com.petrov.service.Utils.mapProductDto;

@Service
public class ProductService implements Serializable {

    @Autowired
    public ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(Utils::mapProduct).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Transactional
    public void saveOrUpdate(ProductDto productDto) {
        Product product = mapProductDto(productDto, categoryRepository);
        productRepository.save(product);
    }

    public ProductDto findById(Long id){
        return mapProduct(productRepository.findById(id).orElse(null));
    }

    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
