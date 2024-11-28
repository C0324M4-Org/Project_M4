package com.itachialy.moji_store.dto;


import com.itachialy.moji_store.model.Account;

public class CartUserInfoDTO {
    private String fullName;
    private String phone;
    private String address;

    public CartUserInfoDTO() {
    }

    public CartUserInfoDTO(Account account) {
        this.fullName = account.getFullName();
        this.phone = account.getPhone();
        this.address = account.getAddress();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
