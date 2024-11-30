package com.itachialy.moji_store.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bill")
@Data
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", unique = false)
    private Account account;

    @OneToMany(mappedBy = "bill")
    private List<BillItem> billItems = new ArrayList<>();

    private int totalBill;

    private String name;
    private String phone;
    private String address;
    private String note = "";

    private int status;
    /*
    * 0 PENDING
    * 1 APPROVED
    * 2 REJECTED
    * 3 SHIPPING
    * 4 DONE
    * */

    private LocalDateTime created_at = LocalDateTime.now();
    private LocalDateTime updated_at = LocalDateTime.now();
}
