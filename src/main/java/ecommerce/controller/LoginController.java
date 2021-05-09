package ecommerce.controller;

import ecommerce.entity.WebRequest;
import ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@CrossOrigin
@RestController
@ResponseBody
public class LoginController {
 
  @Autowired
  UserService userService;

  @PostMapping("/api/login")
  public void login(@RequestBody final WebRequest webRequest){
   userService.authenticateUser(webRequest);
    ResponseEntity.status(200).build();
  }

  @PostMapping("/api/signup") 
  public void signup(@RequestBody final WebRequest account) {
    userService.addAccount(account);
    ResponseEntity.ok().build();
 }

  private final Logger logger = Logger.getLogger(this.getClass().getName());
}
