package ecommerce.services;

import ecommerce.entity.Account;
import ecommerce.repositories.AccountRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService{
  
  @Autowired
  private AccountRepository<Account> accountRepository;
  
  public List<Account> findByUsername(String username){
    return accountRepository.findByUsername(username);
  }
  
  public List<Account> findByEmail(String email){
    return accountRepository.findByEmail(email);
  }

  public void addAccount(Account account){
    accountRepository.save(account);
  }


}
