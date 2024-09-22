package com.example.jwt.domain.role;

import jakarta.persistence.Column; // Hinzugefügt
import com.example.jwt.core.generic.ExtendedEntity;
import com.example.jwt.domain.authority.Authority;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role extends ExtendedEntity {

  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "role_authorities",
          joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id")
  )
  private Set<Authority> authorities = new HashSet<>();

  public Role() {
  }

  public Role(String name) {
    this.name = name;
  }

  // Getter und Setter

  public String getName() {
    return name;
  }

  public Role setName(String name) {
    this.name = name;
    return this;
  }

  public Set<Authority> getAuthorities() {
    return authorities;
  }

  public Role setAuthorities(Set<Authority> authorities) {
    this.authorities = authorities;
    return this;
  }
}
