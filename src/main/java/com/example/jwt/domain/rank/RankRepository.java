package com.example.jwt.domain.rank;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface RankRepository extends JpaRepository<Rank, UUID> {
    Optional<Rank> findByName(String name);
}
