package ecommerce.entity.jwt;

import org.springframework.stereotype.Component;

@Component
public class JwtPayload{

    String email;
    String password;
    String authority;
    String subject;
    String issuer;
    String jti;
    long exp;

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public JwtPayload(){

    }

    public JwtPayload(String email, String password, String authority, String jti) {
        this.email = email;
        this.password = password;
        this.authority = authority;
        this.jti = jti;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }
}
