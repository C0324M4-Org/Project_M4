package com.example.moji_store.config.security;


import com.example.moji_store.common.CustomAuthenticationEntryPoint;
import com.example.moji_store.common.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SpringSecurity {
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        // Chuỗi mã hóa mật khẩu
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/css/**", "/js/**", "/img/**").permitAll()  // Đảm bảo các đường dẫn này có thể truy cập mà không cần đăng nhập
                        .requestMatchers("/admin/**").hasRole("ADMIN")  // Yêu cầu role ADMIN
                        .requestMatchers("/user/**").hasRole("USER")  // Yêu cầu Role USER
                        .anyRequest().authenticated()  // Các yêu cầu còn lại phải được xác thực
                )
                .formLogin(form -> form
                        .loginPage("/login")  // Trang login tùy chỉnh
                        .loginProcessingUrl("/perform_login")  // Đường dẫn xử lý đăng nhập
                        .successHandler(customAuthenticationSuccessHandler)  // Xử lý thành công đăng nhập
                        .permitAll()  // Cho phép tất cả người dùng truy cập trang đăng nhập
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login")  // Trang khi logout thành công
                        .deleteCookies("JSESSIONID")  // Xóa cookie sau khi đăng xuất
                        .invalidateHttpSession(true)  // Hủy phiên đăng nhập
                        .clearAuthentication(true)  // Xóa thông tin xác thực
                        .permitAll()  // Cho phép tất cả người dùng thực hiện logout
                )
                .sessionManagement(session -> session
                        .invalidSessionUrl("/login")  // Định hướng đến trang đăng nhập nếu phiên bị hỏng
                        .maximumSessions(1)  // Số lượng phiên tối đa
                        .expiredUrl("/login")  // Trang khi phiên hết hạn
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(customAuthenticationEntryPoint)  // Xử lý khi không có quyền truy cập
                )
                .requestCache(RequestCacheConfigurer::disable)  // Tắt RequestCache để tránh redirect lại trang trước khi đăng nhập
        ;

        return http.build();
    }

}
