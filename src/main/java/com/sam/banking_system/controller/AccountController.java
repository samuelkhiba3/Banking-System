package com.sam.banking_system.controller;

import com.sam.banking_system.dto.AccountDto;
import com.sam.banking_system.dto.CreateAccountDto;
import com.sam.banking_system.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{userId}")
    public ResponseEntity<List<AccountDto>> getAccountsByUserId(@PathVariable Long userId){
        return new ResponseEntity<>(accountService.getAccounts(userId), HttpStatus.OK);
    }
}
