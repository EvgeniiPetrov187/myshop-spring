package com.petrov.producingwebservice;

import io.spring.guides.gs_producing_web_service.ProductSoap;

public class ProductMapper {

    public static ProductSoap map(Product product){
        ProductSoap productSoap = new ProductSoap();
        productSoap.setCode(product.getCode());
        productSoap.setTitle(product.getTitle());
        productSoap.setUrl(product.getUrl());
        productSoap.setPrice(product.getPrice().doubleValue());
        return productSoap;
    }

}
