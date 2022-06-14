package com.petrov.producingwebservice;

import io.spring.guides.gs_producing_web_service.ProductSoap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

import static com.petrov.producingwebservice.ProductMapper.map;

@Service
public class ProductSoapService implements Serializable {

    @Autowired
    private final ProductSoapRepository productRepository;

    public ProductSoapService(ProductSoapRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductSoap findByTitle(String title) {
        return map(productRepository.findByTitle(title));
    }
}
