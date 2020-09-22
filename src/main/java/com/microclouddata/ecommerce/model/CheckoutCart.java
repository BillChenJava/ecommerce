package com.microclouddata.ecommerce.model;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;

@Entity
@Table(name = "checkout_cart")
public class CheckoutCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @Column(name = "product_id")
    long product_id;
    @Column(name = "qty")
    int qty;
    @Column(name = "price")
    double price;
    @Column(name = "creat_time")
    String creat_time;
    @Column(name = "user_id")
    long user_id;
    @Column(name = "order_id")
    long order_id;
    @Column(name = "payment_type")
    String payment_type;
    @Column(name = "dilivery_address")
    String dilivery_address;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCreat_time() {
        return creat_time;
    }

    public void setCreat_time(String creat_time) {
        this.creat_time = creat_time;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getDilivery_address() {
        return dilivery_address;
    }

    public void setDilivery_address(String dilivery_address) {
        this.dilivery_address = dilivery_address;
    }
}
