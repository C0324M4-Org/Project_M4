package com.itachialy.moji_store.model;
import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;
    private String nameProduct;
    private String description;
    private int price;
    private String modelType;
    private String imageProduct;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    Category category;


    public Product() {
    }



    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long id) {
        this.idProduct = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String name) {
        this.nameProduct = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(String image) {
        this.imageProduct = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

