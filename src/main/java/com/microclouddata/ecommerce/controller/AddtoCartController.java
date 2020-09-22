package com.microclouddata.ecommerce.controller;

import com.microclouddata.ecommerce.JWTConfiguration.ShoppingConfiguration;
import com.microclouddata.ecommerce.api.response.ApiResponse;
import com.microclouddata.ecommerce.model.Addtocart;
import com.microclouddata.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/addtocart")
public class AddtoCartController {
    @Autowired
    CartService cartService;

    @RequestMapping("/addProductToCart")
    public ResponseEntity<?> addCartwithProduct(@RequestBody HashMap<String,String> CartRequest) {
        try {
            String keys[]={"productId","userId","qty","price"};
            if(ShoppingConfiguration.validationwithHashMap(keys,CartRequest)){

            }
            long productId = Long.parseLong(CartRequest.get("productId"));
            long userId = Long.parseLong(CartRequest.get("userId"));
            int qty = Integer.parseInt(CartRequest.get("qty"));
            double price = Double.parseDouble(CartRequest.get("price"));
            List<Addtocart> obj = cartService.addCartByUserIdAndProductId(productId,userId,qty,price);
            return ResponseEntity.ok(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage()));
        }
    }

    @RequestMapping("/removeProductFromCart")
    public ResponseEntity<?> removeCartwithProduct(@RequestBody HashMap<String,String> CartRequest) {
        try {
            String keys[]={"productId","userId","cartId"};
            if(ShoppingConfiguration.validationwithHashMap(keys,CartRequest)){

            }
            long cartId = Long.parseLong(CartRequest.get("cartId"));
            long userId = Long.parseLong(CartRequest.get("userId"));
            long productId = Long.parseLong(CartRequest.get("productId"));
            List<Addtocart> obj = cartService.removeCartByUserId(cartId,userId);
            return ResponseEntity.ok(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage()));
        }
    }

    @RequestMapping("/getCartsByUserId")
    public ResponseEntity<?> getCartsByUserId(@RequestBody HashMap<String,String> CartRequest) {
        try {
            String keys[]={"userId"};
            if(ShoppingConfiguration.validationwithHashMap(keys,CartRequest)){

            }
            long userId = Long.parseLong(CartRequest.get("userId"));
            List<Addtocart> obj = cartService.getCartByUserId(userId);
            return ResponseEntity.ok(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage()));
        }
    }

    @RequestMapping("/updateQtyForCart")
    public ResponseEntity<?> updateQtyForCart(@RequestBody HashMap<String,String> CartRequest) {
        try {
            String keys[]={"cartId","userId","qty","price"};
            if(ShoppingConfiguration.validationwithHashMap(keys,CartRequest)){

            }
            long cartId = Long.parseLong(CartRequest.get("cartId"));
            long userId = Long.parseLong(CartRequest.get("userId"));
            int qty = Integer.parseInt(CartRequest.get("qty"));
            double price = Double.parseDouble(CartRequest.get("price"));
            cartService.updateQtyByCartId(cartId,qty,price);
            List<Addtocart> obj = cartService.getCartByUserId(userId);
            return ResponseEntity.ok(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage()));
        }
    }
}
