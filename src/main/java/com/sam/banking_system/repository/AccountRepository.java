package com.sam.banking_system.repository;

import com.sam.banking_system.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AccountRepository interface that provides data access to Account entities.
 *
 * @author LS Khiba
 * @version 1.0
 */
@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    /**
     * Finds Accounts by user ID.
     *
     * @param userId the user ID
     * @return a list of Accounts
     */
    List<Account> findByUserId(Long userId);
}
