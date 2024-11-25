package com.itachialy.moji_store.common;

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
        String targetUrl = (String) request.getSession().getAttribute("REDIRECT_URL");

        if (targetUrl == null || targetUrl.isEmpty()) {
            String username = authentication.getName();
            boolean isAdmin = authentication.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

            if (isAdmin) {
                targetUrl = "/admin"; // Nếu là ADMIN, chuyển hướng đến trang admin
            } else {
                targetUrl = "/"; // Nếu là USER, chuyển hướng đến trang chủ
            }
        }

        request.getSession().removeAttribute("REDIRECT_URL");

        clearAuthenticationAttributes(request);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}

