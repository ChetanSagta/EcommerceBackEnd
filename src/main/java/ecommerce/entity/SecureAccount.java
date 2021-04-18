package ecommerce.entity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecureAccount implements UserDetails{

  @Autowired
  Account account;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(() -> account.getAuthority());
	}

	@Override
	public String getPassword() {
		return account.getPassword();
	}

	@Override
	public String getUsername() {
		return account.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return account.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return account.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return account.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return account.isEnabled();
	}



}
