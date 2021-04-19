package ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ecommerce.entity.Account;

@Repository
public interface AccountRepository<A> extends JpaRepository<Account, Long> {

  public Optional<Account> findByEmail(String email);

}
