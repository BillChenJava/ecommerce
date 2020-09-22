package com.microclouddata.ecommerce.controller;

import com.microclouddata.ecommerce.model.Category;
import com.microclouddata.ecommerce.model.Product;
import com.microclouddata.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(maxAge = 3600,origins = "*")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/test/{test}")
    public String getTest(@PathVariable("test") String test) throws Exception{
//        String returnvalue = "return:"+test +" "+ testModel.getTestid()+" "+testModel.getTestname();
        return test;
    }

    @GetMapping("/test/mockito")
    public String getMockito() throws Exception{
        return productService.testProduct("test");
    }

    @GetMapping("/getAll")
    public List<Product> getAllProduct() throws Exception {
        System.out.println("123123123");
        return productService.getAllProducts();
    }
    @CrossOrigin
    @RequestMapping("/getAllCategory")
    public List<Category> getAllCategory() throws Exception {
        return productService.getAllCategory();
    }

    @RequestMapping("/getProductsByCategory")
    public List<Product> getProductsByCategory(@RequestBody HashMap<String,String> request){
        String category_id = request.get("cat_id");
        return productService.getProductsByCategory(category_id);
    }

    @GetMapping(value = "/getImage/{img_name}",produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImageWithMediaType(@PathVariable("img_name") String img_name) throws Exception{
        InputStream in = getClass().getResourceAsStream("images"+img_name);
        return null;
    }


}

