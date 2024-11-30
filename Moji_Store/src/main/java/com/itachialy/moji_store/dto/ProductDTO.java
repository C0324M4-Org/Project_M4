package com.itachialy.moji_store.dto;

import com.itachialy.moji_store.model.Category;
import jakarta.validation.constraints.*;
import lombok.*;
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
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

}
