package com.microclouddata.ecommerce.model;

import javax.persistence.*;

@Entity
@Table(name = "addtocart")
public class Addtocart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="user_id")
    private long user_id;
    @Column(name="product_id")
    private long product_id;
    @Column(name="qty")
    private int qty;
    @Column(name="price")
    private double price;
    @Column(name="created_time")
    private String created_time;

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

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public void setCreated_time() {
    }
}
