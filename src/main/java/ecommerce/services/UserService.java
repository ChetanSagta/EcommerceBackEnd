package ecommerce.services;

import ecommerce.entity.User;
import ecommerce.entity.WebRequest;
import ecommerce.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserService {

  @Autowired
  private UserRepo userRepo;
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private ecommerce.services.MyUserDetailsService userDetailsService;
  @Autowired
  private BCryptPasswordEncoder bcryptEncoder;

  public UserService() {
  }

  public UserService(BCryptPasswordEncoder bcryptEncoder) {
    this.bcryptEncoder = bcryptEncoder;
  }

  public Optional<User> findByEmail(String email) {
    return userRepo.findByEmail(email);
  }

  public void addAccount(WebRequest webRequest) {

    User user = new User();
    Optional<User> accountsFound = userRepo.findByEmail(webRequest.getEmail());
    if (accountsFound.isPresent()) {
      throw new RuntimeException("Email already registered. Please use different email.");
    }

    user.setEmail(webRequest.getEmail());
    user.setPassword(bcryptEncoder.encode(webRequest.getPassword()));
    user.setUsername(webRequest.getUsername());
    user.setAuthority("READ");
    userRepo.save(user);
  }

  public User readUserByUsername(String username) {
    return userRepo.findByUsername(username).orElseThrow(EntityNotFoundException::new);
  }

  public User findEmail(WebRequest webRequest) {

    Optional<User> account = userRepo.findByEmail(webRequest.getEmail());
    if (account.isPresent() && bcryptEncoder.matches(
            webRequest.getPassword(), account.get().getPassword())) {
      return account.get();
    }
    throw new RuntimeException("Email or Password is Wrong");
  }

  public UserDetails authenticateUser(WebRequest webRequest) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(webRequest.getUsername());
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
            new UsernamePasswordAuthenticationToken(userDetails, webRequest.getPassword(), userDetails.getAuthorities());

    authenticationManager.authenticate(usernamePasswordAuthenticationToken);

    if (usernamePasswordAuthenticationToken.isAuthenticated()) {
      SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      logger.info(String.format("Auto login %s successfully!", webRequest.getUsername()));
    }

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    logger.info ("Principal : " + authentication.getPrincipal());
    return userDetails;
  }
    private final Logger logger = Logger.getLogger(this.getClass().getName());
  }