package com.itachialy.moji_store.config;

import com.itachialy.moji_store.common.CustomAuthenticationFailureHandler;
import com.itachialy.moji_store.common.CustomAuthenticationSuccessHandler;
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
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    @Autowired
    public SpringSecurity(CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler, CustomAuthenticationFailureHandler customAuthenticationFailureHandler) {
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
        this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
    }




    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/","/login","/register-form", "/register", "/css/**", "/js/**", "/img/**", "/images/**").permitAll()  // Đảm bảo các đường dẫn này có thể truy cập mà không cần đăng nhập
                        .requestMatchers("/admin/**").hasRole("ADMIN")  // Yêu cầu role ADMIN
                        .requestMatchers("/user/**").hasRole("USER")  // Yêu cầu Role USER
                        .anyRequest().authenticated()  // Các yêu cầu còn lại phải được xác thực
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/perform_login")
                        .successHandler(customAuthenticationSuccessHandler)
                        .failureHandler(customAuthenticationFailureHandler)  // Sử dụng custom failure handler
//                        .failureUrl("/login?error=true")

                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout=true")

                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll()
                )
                .sessionManagement(session -> session
                        .invalidSessionUrl("/login")
                        .maximumSessions(1)
                        .expiredUrl("/login")
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/error404")
                )
                .requestCache(RequestCacheConfigurer::disable)
        ;

        return http.build();
    }

}
