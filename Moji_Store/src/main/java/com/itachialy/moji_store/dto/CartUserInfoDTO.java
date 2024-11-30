package com.itachialy.moji_store.dto;


import com.itachialy.moji_store.model.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CartUserInfoDTO {
    private String fullName;
    private String phone;
    private String address;

    public CartUserInfoDTO(Account account) {
        this.fullName = account.getFullName();
        this.phone = account.getPhone();
        this.address = account.getAddress();
    }
}
