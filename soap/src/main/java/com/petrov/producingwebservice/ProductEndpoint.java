package com.petrov.producingwebservice;

import io.spring.guides.gs_producing_web_service.GetProductSoapRequest;
import io.spring.guides.gs_producing_web_service.GetProductSoapResponse;
import io.spring.guides.gs_producing_web_service.ProductSoap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.io.Serializable;

@Endpoint
public class ProductEndpoint implements Serializable {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    @Autowired
    private ProductSoapService productService;

    public ProductEndpoint(ProductSoapService productService) {
        this.productService = productService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductSoapRequest")
    @ResponsePayload
    public GetProductSoapResponse getProductSoap(@RequestPayload GetProductSoapRequest request) {
        GetProductSoapResponse response = new GetProductSoapResponse();
        ProductSoap product = productService.findByTitle(request.getTitle());
        response.setProduct(product);

        return response;
    }

}
