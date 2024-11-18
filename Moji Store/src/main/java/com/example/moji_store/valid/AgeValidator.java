package com.example.moji_store.valid;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class AgeValidator implements ConstraintValidator<ValidAge, LocalDate> {
    private static final int MIN_AGE = 15;
    private static final int MAX_AGE = 100;

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; // Ngày sinh không được để trống
        }

        // Ngày sinh không được quá xa về quá khứ
        LocalDate maxValidDate = LocalDate.now().minusYears(MAX_AGE);
        if (value.isBefore(maxValidDate)) {
            return false;
        }


        // Tính toán độ tuổi và kiểm tra tuổi tối thiểu
        int age = Period.between(value, LocalDate.now()).getYears();
        return age >= MIN_AGE;
    }
}