package ecommerce.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ecommerce.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> , Serializable {

  Optional<User> findByEmail(String email);

  Optional<User> findByUsername(String username);

}
