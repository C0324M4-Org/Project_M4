package com.example.moji_store.valid;
<<<<<<< HEAD

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
=======
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidatorAge.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAge {
    String message() default "*Người dùng phải lớn hơn 12 tuổi";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int age() default 12;  // Mặc định là 12 tuổi
}
>>>>>>> 54eb4666dc03f63ecfa67d9b17e91979924cdc75
