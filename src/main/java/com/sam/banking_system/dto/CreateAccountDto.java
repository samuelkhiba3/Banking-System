package com.sam.banking_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountDto {
    private Long userId;
    private String accountNumber;
    private BigDecimal initialDeposit;
}
