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

    @OneToOne // Each user has one cart
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Account user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    @Column(name = "total_price")
    private double totalPrice;

    public Cart() {}

    public Cart(Account user) {
        this.user = user;
    }

    // Getters and Setters
    public List<CartItem> getItems() {
        return items;
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
            cartItem.setCart(this); // Link cart item to this cart
        }
        calculateTotalPrice();
    }

    public void removeItem(Long productId) {
        items.removeIf(item -> item.getProduct().getId().equals(productId));
        calculateTotalPrice();
    }

    public void clearItems() {
        items.clear();
        totalPrice = 0;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    private void calculateTotalPrice() {
        this.totalPrice = items.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    // Other methods...
}