package ecommerce.entity.jwt;

import org.springframework.stereotype.Component;

@Component
public class JwtPayload{

    String email;
    String authority;
    String user;
    String jti;
    long exp;

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public JwtPayload(){}

    public JwtPayload(String email, String authority, String jti) {
        this.email = email;
        this.authority = authority;
        this.jti = jti;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
