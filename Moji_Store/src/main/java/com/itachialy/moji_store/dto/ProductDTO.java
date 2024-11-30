package com.itachialy.moji_store.dto;

import com.itachialy.moji_store.model.Category;
import com.itachialy.moji_store.model.Product;
import org.springframework.web.multipart.MultipartFile;

public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private int price;
    private String image;
    private Long categoryId;

    // Constructor nhận đối tượng Product để chuyển Entity thành DTO
    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.image = product.getImage();
        // Nếu Category là đối tượng liên kết, bạn lấy categoryId từ nó
        this.categoryId = product.getCategory() != null ? product.getCategory().getId() : null;
    }

    // Getters và Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}


