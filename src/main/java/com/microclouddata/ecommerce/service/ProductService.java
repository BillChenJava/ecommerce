package com.microclouddata.ecommerce.service;

import com.microclouddata.ecommerce.model.Category;
import com.microclouddata.ecommerce.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<Product> getAllProducts() throws Exception;

    List<Product> getProductsByCategory(String category_id);

    List<Category> getAllCategory() throws Exception;

    String testProduct(String testparam) throws Exception;
}
