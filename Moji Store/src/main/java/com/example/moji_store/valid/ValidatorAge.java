package com.example.moji_store.valid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class ValidatorAge implements ConstraintValidator<ValidAge, LocalDate> {
    private int age;

    @Override
    public void initialize(ValidAge constraintAnnotation) {
        this.age = constraintAnnotation.age();
    }

    @Override
    public boolean isValid(LocalDate dob, ConstraintValidatorContext context) {


        LocalDate today = LocalDate.now();
        LocalDate thresholdDate = today.minusYears(age);
        return dob.isBefore(thresholdDate);
    }
}
