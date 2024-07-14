package com.sam.banking_system.service;

import com.sam.banking_system.dto.AccountDto;
import com.sam.banking_system.dto.CreateAccountDto;
import com.sam.banking_system.mapper.AccountMapper;
import com.sam.banking_system.model.Account;
import com.sam.banking_system.model.User;
import com.sam.banking_system.repository.AccountRepository;
import com.sam.banking_system.repository.UserRepository;
import com.sam.banking_system.utility.AccountUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public AccountDto createAccount(CreateAccountDto createAccountDto) {
        User user = userRepository.findById(createAccountDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        createAccountDto.setAccountNumber(AccountUtil.generateAccNumber(LocalDateTime.now(),user.getId()));
        Account account = AccountMapper.DtoToEntity(createAccountDto,user);
        accountRepository.save(account);
        user.getAccounts().add(account);
        userRepository.save(user);
        return AccountMapper.entityToDto(account);
    }

    public List<AccountDto> getAccounts(Long userId){
        return accountRepository.findByUserId(userId)
                .stream()
                .map(AccountMapper::entityToDto)
                .toList();
    }

}