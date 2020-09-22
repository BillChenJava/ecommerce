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

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    CartService cartService;

    @RequestMapping("/checkout_order")
    @Transactional
    public ResponseEntity<?> checkout_order(@RequestBody HashMap<String, String> orderRequest) {
        try {
            /**
             * 需要修改代码，把业务流程都移到 service里面去。在把这个用户 Addtocart 里面的数据删除
             */
            String keys[] = {"userId", "total_price", "payment_type", "delivery_address"};
            if (ShoppingConfiguration.validationwithHashMap(keys, orderRequest)) {

            }
            Long userId = Long.parseLong(orderRequest.get("userId"));
            double total_price = Double.parseDouble(orderRequest.get("total_price"));

            if (cartService.checkTotalAmountAgainstCart(total_price, userId)) {
                List<Addtocart> cartItem = cartService.getCartByUserId(Long.parseLong(orderRequest.get("userId")));
                for (Addtocart addCart : cartItem) {
                    cartService.saveProductForCheckoutByUserId(addCart.getProduct_id(), userId, addCart.getQty(),
                            Long.parseLong(getOrderId()), addCart.getPrice(),
                            orderRequest.get("payment_type"), orderRequest.get("delivery_address"));
                }
                cartService.emptyCartByUserId(userId);
                return ResponseEntity.ok(new ApiResponse("Order placed successfully"));
            } else {
                throw new Exception("Total amount is mismatch.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage()));
        }
    }

    public static String getOrderId() {
        Random r = new Random(System.currentTimeMillis());
        return 10000 + r.nextInt(20000) + "";
    }

    @RequestMapping("/getOrderByUserId")
    public ResponseEntity<?> getOrderByUserId() {
        try {
            String keys[] = {"userId"};
            return ResponseEntity.ok(new ApiResponse("Order placed successfully"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage()));
        }
    }
}
