package com.itachialy.moji_store.model;

import jakarta.persistence.*;
import org.aspectj.weaver.ast.Or;
import org.hibernate.mapping.List;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class OrderItem {

    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;


    @Column(name = "quantity")
    private int quantity;


    private LocalDateTime created_at = LocalDateTime.now();
    private LocalDateTime updated_at = LocalDateTime.now();

    public OrderItem() {
    }


    public OrderItem(Long id, Order order, Product product, int quantity, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Order getOrder() {
        return order;
    }


    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }


    public LocalDateTime getUpdated_at() {
        return updated_at;
    }


    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}
