package com.petrov.producingwebservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSoapRepository extends JpaRepository<Product, Long> {

    Product findByTitle(String title);
}