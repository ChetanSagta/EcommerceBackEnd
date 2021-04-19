package ecommerce.services;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecommerce.entity.Account;
import ecommerce.entity.WebRequestAccount;
import ecommerce.repositories.AccountRepository;

@Service
public class AccountService{
  
  @Autowired
  private AccountRepository<Account> accountRepository;
  
  public Optional<Account> findByEmail(String email){
    return accountRepository.findByEmail(email);
  }

  public void addAccount(WebRequestAccount webRequestAccount){

    Account account = new Account();

    Optional<Account> accountsFound =  accountRepository.findByEmail(account.getEmail());

    if(accountsFound.isPresent()){
      throw new RuntimeException("Account Already Present");
    }

    account.setEmail(webRequestAccount.getEmail());
    account.setPassword(webRequestAccount.getPassword());
    account.setUsername(webRequestAccount.getUsername());
    account.setEnabled(true);
    account.setAccountNonExpired(true);
    account.setAccountNonLocked(true);
    account.setCredentialsNonExpired(true);
    account.setAdmin(false);

    accountRepository.save(account);

  }

  public void finalEmail(WebRequestAccount webRequestAccount){

    Optional<Account> account = accountRepository.findByEmail(webRequestAccount.getEmail());
    if(account.isPresent()) {


    }
    throw new RuntimeException("Email or Password is Wrong");
  }

  private Logger logger = Logger.getLogger(this.getClass().getName());
}
