package ecommerce.services;

import ecommerce.entity.SecureUser;
import ecommerce.entity.User;
import ecommerce.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService, Serializable {

    @Autowired
    private final UserRepo userRepo;

    public MyUserDetailsService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUsername(username);
        return user.map(SecureUser::new).orElseThrow(
                ()-> new UsernameNotFoundException("User : "+ username + "not found"));

    }
}
