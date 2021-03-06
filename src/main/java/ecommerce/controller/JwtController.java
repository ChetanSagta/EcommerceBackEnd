package ecommerce.controller;

import ecommerce.entity.SecureUser;
import ecommerce.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Secured("ROLE_READ")
@RequestMapping("/api")
public class JwtController {
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @PostMapping("/encodeToken")
    public ResponseEntity<String> getJwtToken(@RequestBody SecureUser user){
        String token = jwtTokenUtil.generateToken(user);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization Token", token);
        return new ResponseEntity<>(responseHeaders, HttpStatus.OK);
    }

    @PostMapping("/decodeToken")
    public Claims decodeJwtToken(@RequestBody String jwtToken){
       return jwtTokenUtil.getClaimFromToken(jwtToken, DefaultClaims::new);

    }

}
