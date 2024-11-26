package com.itachialy.moji_store.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    private final String VNPAY_URL = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html"; // Use sandbox for testing
    private final String tmnCode = "YOUR_TMN_CODE"; // Replace with your TMN Code
    private final String hashSecret = "YOUR_HASH_SECRET"; // Replace with your Hash Secret

    public String createPaymentUrl(double amount, String orderId, String returnUrl) {
        Map<String, String> params = new HashMap<>();
        params.put("vnp_Version", "2.1.0");
        params.put("vnp_Command", "pay");
        params.put("vnp_TmnCode", tmnCode);
        params.put("vnp_Amount", String.valueOf((int) (amount * 100))); // Amount in VND
        params.put("vnp_OrderId", orderId);
        params.put("vnp_ReturnUrl", returnUrl);
        params.put("vnp_CreateDate", getCurrentDateTime());

        // Additional parameters can be added here as needed

        // Build the URL with parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(VNPAY_URL);
        params.forEach(builder::queryParam);

        return builder.toUriString();
    }

    private String getCurrentDateTime() {
        // Return current date time in the required format
        return "YYYYMMDDHHMMSS"; // Replace with actual date time formatting logic
    }
}