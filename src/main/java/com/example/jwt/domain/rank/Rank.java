package com.example.jwt.domain.rank;

import com.example.jwt.core.generic.ExtendedAuditEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "rank")
public class Rank extends ExtendedAuditEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public Rank() {
    }

    public Rank(String name) {
        this.name = name;
    }

    // Getter und Setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
