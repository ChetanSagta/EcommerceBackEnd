package ecommerce.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Account{

  @Id
  @GeneratedValue
  private Long id;
  private String username;
  private String email;
  private String password;
  private String authority;
  private boolean isAccountNonExpired;
  private boolean isAccountNonLocked;
  private boolean isCredentialsNonExpired;
  private boolean isEnabled;
  private boolean isAdmin;


  public Account(final String username, final String email, final String password){
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public boolean isAccountNonLocked() {
    return isAccountNonLocked;
  }

  public void setAccountNonLocked(final boolean isAccountNonLocked) {
    this.isAccountNonLocked = isAccountNonLocked;
  }

  public Account(){

  }

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(final String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(final String password) {
    this.password = password;
  }

  public boolean isAdmin() {
    return isAdmin;
  }

  public void setAdmin(final boolean admin) {
    isAdmin = admin;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public String getAuthority() {
    return authority;
  }

  public void setAuthority(final String authority) {
    this.authority = authority;
  }

  public boolean isAccountNonExpired() {
    return isAccountNonExpired;
  }

  public void setAccountNonExpired(final boolean isAccountNonExpired) {
    this.isAccountNonExpired = isAccountNonExpired;
  }

  public boolean isCredentialsNonExpired() {
    return isCredentialsNonExpired;
  }

  public void setCredentialsNonExpired(final boolean isCredentialsNonExpired) {
    this.isCredentialsNonExpired = isCredentialsNonExpired;
  }

  public boolean isEnabled() {
    return isEnabled;
  }

  public void setEnabled(final boolean isEnabled) {
    this.isEnabled = isEnabled;
  }
}
