package com.sam.banking_system.controller;

import com.sam.banking_system.dto.AccountDto;
import com.sam.banking_system.dto.CreateAccountDto;
import com.sam.banking_system.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody CreateAccountDto createAccountDto){
        AccountDto accountDto = accountService.createAccount(createAccountDto);
        return ResponseEntity.ok(accountDto);
    }
}
