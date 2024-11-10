package com.example.moji_store.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        // Lấy URL từ session attribute tự định nghĩa (nếu có)
        String targetUrl = (String) request.getSession().getAttribute("REDIRECT_URL");

        // Kiểm tra nếu không có REDIRECT_URL trong session, chuyển hướng dựa trên vai trò
        if (targetUrl == null || targetUrl.isEmpty()) {
            // Lấy tên người dùng đang đăng nhập
            String username = authentication.getName();
            // Kiểm tra vai trò của người dùng
            boolean isAdmin = authentication.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

            if (isAdmin) {
                targetUrl = "/admin"; // Nếu là ADMIN, chuyển hướng đến trang admin
            } else {
                targetUrl = "/"; // Nếu là USER, chuyển hướng đến trang chủ
            }
        }

        // Xóa attribute khỏi session sau khi sử dụng
        request.getSession().removeAttribute("REDIRECT_URL");

        // Tiến hành chuyển hướng người dùng đến URL đã xác định
        clearAuthenticationAttributes(request);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}

