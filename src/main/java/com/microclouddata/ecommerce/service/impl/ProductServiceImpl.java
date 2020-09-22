package com.microclouddata.ecommerce.service.impl;

import com.microclouddata.ecommerce.model.Category;
import com.microclouddata.ecommerce.model.Product;
import com.microclouddata.ecommerce.repository.CategoryRepo;
import com.microclouddata.ecommerce.repository.ProductRepo;
import com.microclouddata.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepo productRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public List<Product> getAllProducts()  {
        return productRepo.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category_id) {
        return productRepo.getProductByCategory(category_id);
    }

    @Override
    public List<Category> getAllCategory(){ return categoryRepo.findAll();}

    @Override
    public String testProduct(String testParam) throws Exception {
        return testParam;
    }


}
