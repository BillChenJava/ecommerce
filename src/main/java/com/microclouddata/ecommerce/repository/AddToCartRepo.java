package com.microclouddata.ecommerce.repository;

import com.microclouddata.ecommerce.model.Addtocart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface AddToCartRepo extends JpaRepository<Addtocart,Long> {

    @Query(value="select sum(qty*price) from Addtocart where user_id=:user_id")
    Double getTotalAmountByUserId(@Param("user_id") Long user_id);

    @Query(value = "select a from Addtocart a where a.user_id=:user_id")
    List<Addtocart> getCartByUserId(@Param("user_id") Long user_id);

    @Query("SELECT a FROM Addtocart a WHERE a.product_id=:product_id and a.user_id=:user_id")
    Optional<Addtocart> getCartByProductIdAndUserId(@Param("product_id") Long product_id, @Param("user_id") Long user_id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Addtocart  WHERE id=:cart_id and user_id=:user_id")
    void deleteCartByIdAndUserId(@Param("cart_id") Long cart_id,@Param("user_id") Long user_id);
    @Modifying
    @Transactional
    @Query("DELETE FROM Addtocart d WHERE d.user_id=:user_id")
    void deleteAllByUserId(@Param("user_id") Long user_id);

    @Modifying
    @Transactional
    @Query("UPDATE Addtocart set qty=:qty,price=:price WHERE id=:cartId")
    void updateQtyByCartId(@Param("cartId") long cartId,@Param("qty") int qty,@Param("price") double price);
}
