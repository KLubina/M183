package com.example.jwt.domain.user;

import com.example.jwt.domain.user.dto.UserRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterDTO userRegisterDTO, BindingResult result, Locale locale) {
    if (result.hasErrors()) {
      Map<String, String> errors = result.getFieldErrors()
              .stream()
              .collect(Collectors.toMap(FieldError::getField, error -> error.getDefaultMessage()));
      return ResponseEntity.badRequest().body(errors);
    }

    try {
      User user = new User();
      user.setFirstName(userRegisterDTO.getFirstName());
      user.setLastName(userRegisterDTO.getLastName());
      user.setEmail(userRegisterDTO.getEmail());
      user.setUsername(userRegisterDTO.getUsername());
      user.setPassword(userRegisterDTO.getPassword());
      // Setze andere Felder falls vorhanden

      userService.register(user);
      return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }
}
