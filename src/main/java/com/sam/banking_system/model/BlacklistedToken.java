package com.sam.banking_system.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

/**
 * Represents a blacklisted token entity.
 * This entity stores tokens that have been blacklisted and are no longer valid.
 * @author LS Khiba
 * @version 1.0
 */
@Entity
@Data
public class BlacklistedToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDate dateBlacklisted;

    @Column(length = 316)
    private String token;
}
