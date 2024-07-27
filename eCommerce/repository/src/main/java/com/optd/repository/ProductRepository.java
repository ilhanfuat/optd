package com.optd.repository;

import com.optd.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("select p from Product p ")
    List<Product> retrieveProductList();

    @Query("select p from Product p where p.productId=:productId")
    Product retrieveProductByProductId(@Param("productId") Integer productId);
}
