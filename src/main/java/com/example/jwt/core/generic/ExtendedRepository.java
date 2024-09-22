package com.example.jwt.core.generic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import java.util.UUID;

@NoRepositoryBean
public interface ExtendedRepository<T> extends JpaRepository<T, UUID> {
    // Zusätzliche generische Methoden können hier definiert werden
}
