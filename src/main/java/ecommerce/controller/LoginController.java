package ecommerce.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.entity.Account;
import ecommerce.services.AccountService;

@CrossOrigin
@RestController
public class LoginController {
 
  @Autowired
  AccountService accountService;

  @PostMapping("/login")
  public String login(@RequestBody final Account account){
      
    List<Account> accountsFound =  accountService.findByEmail(account.getEmail());

    if(accountsFound.size() !=0){

      if(account.getPassword().equals(accountsFound.get(0).getPassword()))
          return "Logged In Successfully";
    }

    return "Email or Password is incorrect";
  }

  @PostMapping("/signup") 
  public String signup(@RequestBody final Account account) {

    List<Account> accountsFound =  accountService.findByEmail(account.getEmail());
    logger.info("Accounts Found: " + accountsFound);

    if(accountsFound.size() == 0){
      accountService.addAccount(account);
      return "Signed Up Successfully";
    }

    return "Email already present";
  }
  private Logger logger = Logger.getLogger(this.getClass().getName());
}
