package com.microclouddata.ecommerce.repository;

import com.microclouddata.ecommerce.model.Addtocart;
import com.microclouddata.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,String> {
    @Query(value = "select a from products a where a.category_id=:category_id")
    List<Product> getProductByCategory(@Param("category_id") String category_id);
}
