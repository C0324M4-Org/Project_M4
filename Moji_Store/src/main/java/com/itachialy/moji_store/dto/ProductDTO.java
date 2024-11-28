package com.itachialy.moji_store.dto;

import com.itachialy.moji_store.model.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductDTO {
    private Long idProduct;
    @NotNull(message = "Tên sản phẩm không được để trống")
    @Size(max = 255, message = "Tên sản phẩm không được vượt quá 255 ký tự")
    private String nameProduct;

    @NotNull(message = "Mô tả không được để trống")
    @Size(max = 500, message = "Mô tả không được vượt quá 500 ký tự")
    private String description;

    @Min(value = 1, message = "Giá sản phẩm phải lớn hơn 0")
    private int price;

    @NotNull(message = "Loại mô hình không được để trống")
    private String modelType;

    private String imageProduct;

    @NotNull(message = "Danh mục không được để trống")
    private Category category;

    public ProductDTO() {
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
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

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public String getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
