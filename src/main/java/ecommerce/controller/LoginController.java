package ecommerce.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.entity.WebRequestAccount;
import ecommerce.services.AccountService;

@CrossOrigin
@RestController
public class LoginController {
 
  @Autowired
  AccountService accountService;

  @PostMapping("/login")
  public void login(@RequestBody final WebRequestAccount webRequestAccount){
    accountService.findByEmail(webRequestAccount.getEmail());  
  }

  @PostMapping("/signup") 
  public ResponseEntity signup(@RequestBody final WebRequestAccount account) {
    accountService.createAccount(account);
    return ResponseEntity.ok().build();
 }
  private Logger logger = Logger.getLogger(this.getClass().getName());
}
