package com.microclouddata.ecommerce.repository;

import com.microclouddata.ecommerce.model.Addtocart;
import com.microclouddata.ecommerce.model.CheckoutCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckoutCartRepo extends JpaRepository<CheckoutCart,Long> {
    @Query(value = "select a from CheckoutCart a where a.user_id=:user_id")
    List<CheckoutCart> getCheckoutCartByUserId(@Param("user_id") Long user_id);
}
