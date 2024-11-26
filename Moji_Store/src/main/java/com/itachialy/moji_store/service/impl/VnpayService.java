//package com.itachialy.moji_store.service.impl;
//
//public class VnpayService {
//    @Service
//    public class VnpayService {
//
//        private final String VNPAY_API_URL = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
//        private final String vnpaySecretKey = "your-vnpay-secret-key";
//        private final String vnpayMerchantCode = "your-vnpay-merchant-code";
//
//        public String createPaymentUrl(Order order) {
//            // Tạo các tham số yêu cầu VNPay
//            Map<String, String> params = new HashMap<>();
//            params.put("vnp_TxnRef", String.valueOf(order.getId()));
//            params.put("vnp_Amount", String.valueOf(order.getTotalPrice() * 100)); // VNPay yêu cầu số tiền tính bằng đồng
//            params.put("vnp_OrderInfo", "Thanh toán đơn hàng #" + order.getId());
//            params.put("vnp_ReturnUrl", "http://your-website.com/payment/vnpayReturn");
//
//            // Tạo chữ ký
//            String signature = createSignature(params);
//            params.put("vnp_SecureHash", signature);
//
//            // Xây dựng URL thanh toán VNPay
//            StringBuilder url = new StringBuilder(VNPAY_API_URL);
//            params.forEach((key, value) -> url.append("&").append(key).append("=").append(value));
//
//            return url.toString();
//        }
//
//        private String createSignature(Map<String, String> params) {
//            // Tạo chữ ký theo quy định của VNPay
//            String data = params.entrySet().stream()
//                    .filter(entry -> entry.getValue() != null && !entry.getValue().isEmpty())
//                    .map(entry -> entry.getKey() + "=" + entry.getValue())
//                    .sorted()
//                    .collect(Collectors.joining("&"));
//            data = vnpaySecretKey + data;
//
//            try {
//                MessageDigest md = MessageDigest.getInstance("SHA-256");
//                byte[] hash = md.digest(data.getBytes(StandardCharsets.UTF_8));
//                return bytesToHex(hash);
//            } catch (NoSuchAlgorithmException e) {
//                throw new RuntimeException("Error while generating signature", e);
//            }
//        }
//
//        private String bytesToHex(byte[] bytes) {
//            StringBuilder hexString = new StringBuilder();
//            for (byte b : bytes) {
//                hexString.append(String.format("%02x", b));
//            }
//            return hexString.toString();
//        }
//
//        public String processVnpayResponse(Map<String, String> params) {
//            // Xử lý kết quả thanh toán trả về từ VNPay
//            if (params.containsKey("vnp_ResponseCode") && params.get("vnp_ResponseCode").equals("00")) {
//                // Thanh toán thành công
//                return "SUCCESS";
//            } else {
//                // Thanh toán thất bại
//                return "FAILURE";
//            }
//        }
//    }
//
//}
