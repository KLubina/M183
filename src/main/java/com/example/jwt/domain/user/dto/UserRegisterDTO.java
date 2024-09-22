package com.example.jwt.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;

public class UserRegisterDTO {

  @NotBlank(message = "{user.firstName.notBlank}")
  private String firstName;

  @NotBlank(message = "{user.lastName.notBlank}")
  private String lastName;

  @Min(value = 18, message = "{user.age.min}")
  private int age;

  @NotBlank(message = "{user.nationality.notBlank}")
  private String nationality;

  @NotBlank(message = "{user.username.notBlank}")
  @Size(min = 4, max = 20, message = "{user.username.size}")
  private String username;

  @NotBlank(message = "{user.email.notBlank}")
  @Email(message = "{user.email.valid}")
  private String email;

  @NotBlank(message = "{user.password.notBlank}")
  @Size(min = 8, message = "{user.password.size}")
  private String password;

  // Getter und Setter

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getNationality() {
    return nationality;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
