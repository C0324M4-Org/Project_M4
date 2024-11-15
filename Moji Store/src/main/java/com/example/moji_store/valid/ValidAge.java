package com.example.moji_store.valid;
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
