package com.microclouddata.ecommerce.service.impl;

import com.microclouddata.ecommerce.model.Addtocart;
import com.microclouddata.ecommerce.model.CheckoutCart;
import com.microclouddata.ecommerce.repository.AddToCartRepo;
import com.microclouddata.ecommerce.repository.CheckoutCartRepo;
import com.microclouddata.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    AddToCartRepo addToCartRepo;
    @Autowired
    CheckoutCartRepo checkoutCartRepo;

    @Override
    public List<Addtocart> addCartByUserIdAndProductId(long productId, long userId, int qty, double price) throws Exception {
        try {
            if (addToCartRepo.getCartByProductIdAndUserId(productId, userId).isPresent()) {
                throw new Exception("Product is already exist.");
            }
            Addtocart obj = new Addtocart();
            obj.setQty(qty);
            obj.setUser_id(userId);
            obj.setProduct_id(productId);
            //时间转字符串格式化
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            String dateTime = LocalDateTime.now(ZoneOffset.of("-4")).format(formatter);
            obj.setCreated_time(dateTime);
            //todo price has to check with qty
            obj.setPrice(price);
            addToCartRepo.save(obj);
            return this.getCartByUserId(userId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void updateQtyByCartId(long cartId, int qty, double price) throws Exception {
        addToCartRepo.updateQtyByCartId(cartId, qty, price);
    }

    @Override
    public List<Addtocart> getCartByUserId(Long userId) throws Exception {
        return addToCartRepo.getCartByUserId(userId);
    }

    @Override
    public List<Addtocart> removeCartByUserId(long cartId, long userId) throws Exception {
        addToCartRepo.deleteCartByIdAndUserId(cartId, userId);
        return addToCartRepo.getCartByUserId(userId);
    }

    @Override
    public List<Addtocart> emptyCartByUserId(long userId) throws Exception {
        addToCartRepo.deleteAllByUserId(userId);
        return addToCartRepo.getCartByUserId(userId);
    }

    @Override
    public Boolean checkTotalAmountAgainstCart(double totalAmount, long userId) {
        double total_amount = addToCartRepo.getTotalAmountByUserId(userId);
        if (total_amount == totalAmount) {
            return true;
        }
        System.out.println("Error from request" + total_amount + " --db-- " + totalAmount);
        return false;
    }



    @Override
    public List<CheckoutCart> saveProductForCheckoutByUserId(long productId, long userId, int qty,
                                                             long orderId,double price, String paymentType,
                                                             String deliveryAddress) throws Exception {
        try {
            CheckoutCart cart = new CheckoutCart();
            cart.setProduct_id(productId);
            cart.setUser_id(userId);
            cart.setQty(qty);
            cart.setOrder_id(orderId);
            cart.setPrice(price);
            cart.setPayment_type(paymentType);
            cart.setDilivery_address(deliveryAddress);
            //时间转字符串格式化
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            String dateTime = LocalDateTime.now(ZoneOffset.of("-4")).format(formatter);
            cart.setCreat_time(dateTime);
            checkoutCartRepo.save(cart);
            return this.getAllCheckoutByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error while checkout "+e.getMessage());
        }

    }

    @Override
    public List<CheckoutCart> getAllCheckoutByUserId(long userId) {
        return checkoutCartRepo.getCheckoutCartByUserId(userId);
    }


}
