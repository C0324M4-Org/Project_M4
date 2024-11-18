package com.example.moji_store.dto;
import com.example.moji_store.valid.ValidAge;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class RegisterDTO {
    @NotNull(message = "*Không được để trống tên đăng nhập")
    @Size(min = 5, message = "*Tên đăng nhập phải có ít nhất 5 ký tự")
    @Pattern(regexp = "^[a-z0-9]+$", message = "*Tên đăng nhập không được chứa ký tự đặc biệt và chữ Hoa")
    private String username;

    @NotNull(message = "*Không được để trống Họ và tên")
    @Size(min = 2, message = "*Họ và tên phải có ít nhất 2 ký tự")
    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "*Họ và tên không được chứa số hoặc ký tự đặc biệt")
    private String fullName;

    @NotNull(message = "*Không được để trống Email")
    @Email(message = "*Email không hợp lệ")
    @Size(min = 5, message = "*Email phải có ít nhất 5 ký tự")
    private String email;

    @NotNull(message = "*Đừng quên giới tính của mình nhé")
    private String gender;

    @NotNull(message = "*Vui lòng nhập mật khẩu")
    @Size(min = 3, max = 20, message = "Mật khẩu phải từ 3 đến 20 ký tự")
    private String password;

    @NotNull(message = "*Vui lòng xác nhận lại mật khẩu")
    private String confirmPassword;

    @NotNull(message = "*Vui lòng nhập ngày sinh")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "*Ngày sinh không được quá ngày hiện tại")
<<<<<<< HEAD
    @ValidAge
    private LocalDate dob;

    public RegisterDTO(String username, String fullName,String gender, String email, String password, String confirmPassword, LocalDate dob) {
=======
//    @ValidAge
    private LocalDate dob;

    public RegisterDTO(String username, String fullName,String gender, String email,
                       String password, String confirmPassword, LocalDate dob) {
>>>>>>> 54eb4666dc03f63ecfa67d9b17e91979924cdc75
        this.username = username;
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.dob = dob;
    }

    public RegisterDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public boolean isPasswordMatch() {
        return this.password.equals(this.confirmPassword);
    }

}