package com.mora.ordermanager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mora.ordermanager.Entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
