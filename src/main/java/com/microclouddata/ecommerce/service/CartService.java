package com.microclouddata.ecommerce.service;

import com.microclouddata.ecommerce.model.Addtocart;
import com.microclouddata.ecommerce.model.CheckoutCart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    List<Addtocart> addCartByUserIdAndProductId(long productId, long userId, int qty, double price) throws Exception;
    void updateQtyByCartId(long cartId,int qty,double price) throws Exception;
    List<Addtocart> getCartByUserId(Long userId) throws Exception;
    List<Addtocart> removeCartByUserId(long cartId, long userId) throws Exception;
    List<Addtocart> emptyCartByUserId(long userId) throws Exception;
    Boolean checkTotalAmountAgainstCart(double totalAmount,long userId);
    List<CheckoutCart> saveProductForCheckoutByUserId(long productId, long userId, int qty,long orderId,double price,
                                                      String paymentType,String deliveryAddress) throws Exception;
    List<CheckoutCart> getAllCheckoutByUserId(long userId);
    //    Addtocart CheckOutCart();

}
