package com.itachialy.moji_store.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Account user; // Reference to the user who placed the order

    private String fullName;
    private String phone;
    private String address;

    private LocalDateTime orderDate;
    private double totalPrice;

    // Constructors, getters, and setters

    public Order() {}

    public Order(Account user, String fullName, String phone, String address, double totalPrice) {
        this.user = user;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.orderDate = LocalDateTime.now();
        this.totalPrice = totalPrice;
    }

    public Order(Long id, Account user, String fullName, String phone, String address, LocalDateTime orderDate, double totalPrice) {
        this.id = id;
        this.user = user;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    public Order(Account currentUser, String phone, String address, double totalPrice) {

    }
// Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}