package com.example.jwt.domain.authority;

import com.example.jwt.core.generic.ExtendedEntity;
import jakarta.persistence.*;
import jakarta.persistence.Column; // Hinzugefügt

@Entity
@Table(name = "authorities")
public class Authority extends ExtendedEntity {

  @Column(name = "name", nullable = false, unique = true)
  private String name;

  public Authority() {
  }

  public Authority(String name) {
    this.name = name;
  }

  // Getter und Setter

  public String getName() {
    return name;
  }

  public Authority setName(String name) {
    this.name = name;
    return this;
  }
}
