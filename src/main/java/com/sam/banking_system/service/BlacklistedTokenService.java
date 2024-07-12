package com.sam.banking_system.service;

import com.sam.banking_system.model.BlacklistedToken;
import com.sam.banking_system.repository.BlacklistedTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing blacklisted tokens.
 * This service provides methods for blacklisting tokens and checking if a token is blacklisted.
 * @author LS Khiba
 * @version 1.0
 */
@Service
public class BlacklistedTokenService {

    private final BlacklistedTokenRepository blacklistedTokenRepository;

    /**
     * Constructor that injects the blacklisted token repository.
     *
     * @param blacklistedTokenRepository the repository for blacklisted tokens
     */
    public BlacklistedTokenService(BlacklistedTokenRepository blacklistedTokenRepository) {
        this.blacklistedTokenRepository = blacklistedTokenRepository;
    }

    /**
     * Method to blacklist a token.
     *
     * @param token the token to blacklist
     */
    @Transactional
    public void blacklistToken(String token){
        BlacklistedToken blacklistedToken = new BlacklistedToken();
        blacklistedToken.setToken(token);
        blacklistedTokenRepository.save(blacklistedToken);
    }

    /**
     * Method to check if a token is blacklisted.
     *
     * @param token the token to check
     * @return true if the token is blacklisted, false otherwise
     */
    public Boolean isTokenBlacklisted(String token){
        return blacklistedTokenRepository.findByToken(token).isPresent();
    }
}
