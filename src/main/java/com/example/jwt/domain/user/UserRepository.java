package com.example.jwt.domain.user;

import com.example.jwt.core.generic.ExtendedRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ExtendedRepository<User> {

  Optional<User> findByEmail(String email);
  Optional<User> findByUsername(String username); // Hinzugefügt

  boolean existsByUsername(String username); // Hinzugefügt
  boolean existsByEmail(String email); // Optional, falls du dies verwenden möchtest
}
