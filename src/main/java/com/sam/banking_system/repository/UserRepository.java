package com.sam.banking_system.repository;

import com.sam.banking_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * UserRepository interface that provides data access to User entities.
 *
 * @author LS Khiba
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * Finds a user by email.
     *
     * @param email the email address
     * @return an optional user
     */
    Optional<User> findByEmail(String email);
}
