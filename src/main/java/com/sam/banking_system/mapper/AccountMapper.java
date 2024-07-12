package com.sam.banking_system.mapper;

import com.sam.banking_system.dto.AccountDto;
import com.sam.banking_system.dto.CreateAccountDto;
import com.sam.banking_system.model.Account;
import com.sam.banking_system.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountMapper {

    public static AccountDto entityToDto(Account account){
        return new AccountDto(
                account.getId(),
                account.getAccountNumber(),
                account.getBalance(),
                account.getCreatedDate(),
                account.getUpdatedDate()
        );
    }

    public static Account DtoToEntity(CreateAccountDto createAccountDto, User user){
        Account account = new Account();
        account.setUser(user);
        account.setAccountNumber(createAccountDto.getAccountNumber());
        account.setUpdatedDate(LocalDateTime.now());
        account.setCreatedDate(LocalDateTime.now());
        account.setBalance(createAccountDto.getInitialDeposit() != null ? createAccountDto.getInitialDeposit() : BigDecimal.ZERO);
        return account;
    }
}
