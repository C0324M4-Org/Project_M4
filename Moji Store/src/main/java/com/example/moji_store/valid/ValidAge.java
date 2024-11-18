package com.example.moji_store.valid;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AgeValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAge {
    String message() default "*Bạn chưa đủ tuổi để tham gia, vui lòng quay lại khi bạn đủ 15     tuổi";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}