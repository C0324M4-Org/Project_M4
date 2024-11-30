package com.itachialy.moji_store.dto;
import com.itachialy.moji_store.valid.ValidAge;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
@Data
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
    @ValidAge
    private LocalDate dob;

    public boolean isPasswordMatch() {
        return this.password.equals(this.confirmPassword);
    }

}