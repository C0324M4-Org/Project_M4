//package com.itachialy.moji_store.controller;
//
//import com.itachialy.moji_store.model.Cart;
//import com.itachialy.moji_store.model.Order;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//    @RestController
//    @RequestMapping("/payment")
//    public class PaymentController {
//
//        @Value("${vnpay.secret.key}")
//        private String vnpaySecretKey;
//
//        @Value("${vnpay.merchant.code}")
//        private String vnpayMerchantCode;
//
//        @PostMapping("/createPayment")
//        public String createPayment(@RequestBody Cart cart) {
//            // Tạo đơn hàng và lưu vào cơ sở dữ liệu
//            Order order = orderService.createOrder(cart);
//
//            // Tạo yêu cầu thanh toán VNPay
//            String paymentUrl = vnpayService.createPaymentUrl(order);
//
//            // Chuyển hướng đến VNPay
//            return "redirect:" + paymentUrl;
//        }
//
//        @GetMapping("/vnpayReturn")
//        public String vnpayReturn(@RequestParam Map<String, String> params) {
//            // Xử lý kết quả trả về từ VNPay
//            String vnpayResponse = vnpayService.processVnpayResponse(params);
//
//            // Cập nhật trạng thái đơn hàng
//            orderService.updatePaymentStatus(vnpayResponse);
//
//            // Chuyển hướng người dùng đến trang thông báo
//            return "redirect:/payment/result";
//        }
//    }
