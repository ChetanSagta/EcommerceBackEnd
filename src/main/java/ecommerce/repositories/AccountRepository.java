package ecommerce.repositories;

import ecommerce.entity.Account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository<A> extends JpaRepository<Account, Long> {

  public int countByUsername(String username);

  public List<Account> findByUsername(String username);
  
  public List<Account> findByEmail(String email);

}
