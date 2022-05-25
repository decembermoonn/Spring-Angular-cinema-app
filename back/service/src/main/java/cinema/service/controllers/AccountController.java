package cinema.service.controllers;

import cinema.service.models.dtos.Credentials;
import cinema.service.repositories.AccountRepository;
import cinema.service.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class AccountController {

  private final PasswordEncoder passwordEncoder;
  private final AccountRepository accountRepository;

  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody Credentials credentials) {
    Optional<User> userOptional = accountRepository.findById(credentials.getUsername());
    if (userOptional.isPresent()) {
      User user = userOptional.get();
      if (arePasswordsEqual(user.getPassword(), credentials.getPassword())) {
        return ResponseEntity.ok(user);
      }
    }
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid credentials");
  }

  private boolean arePasswordsEqual(String dbPassword, String reqPassword) {
    return passwordEncoder.matches(reqPassword, dbPassword);
  }
}
