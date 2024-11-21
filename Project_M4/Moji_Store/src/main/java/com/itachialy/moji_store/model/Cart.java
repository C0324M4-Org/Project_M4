package com.itachialy.moji_store.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    @Column(name = "total_price")
    private double totalPrice;

    public Cart() {}

    public Cart(String userId) {
        this.userId = userId;
    }

    // Getters và Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
        calculateTotalPrice();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    private void calculateTotalPrice() {
        this.totalPrice = items.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    public void addItem(CartItem cartItem) {
        CartItem existingItem = items.stream()
                .filter(item -> item.getProduct().getId().equals(cartItem.getProduct().getId()))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + cartItem.getQuantity());
        } else {
            this.items.add(cartItem);
            cartItem.setCart(this); // Liên kết với cart
        }
        calculateTotalPrice();
    }

    public void removeItem(Long productId) {
        items.removeIf(item -> item.getProduct().getId().equals(productId));
        calculateTotalPrice();
    }

    public void updateItemQuantity(Long productId, int quantity) {
        CartItem item = items.stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (item != null) {
            item.setQuantity(quantity);
            calculateTotalPrice();
        }
    }

    public void clear() {
        items.clear();
        totalPrice = 0;
    }

    public int getItemsSize() {
        return items.size();
    }
}

