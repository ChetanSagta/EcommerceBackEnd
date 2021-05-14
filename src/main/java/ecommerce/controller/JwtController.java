package ecommerce.controller;

import ecommerce.entity.SecureUser;
import ecommerce.entity.jwt.JwtPayload;
import ecommerce.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @PostMapping("/api/encodeToken")
    public ResponseEntity<String> getJwtToken(@RequestBody SecureUser user){
        String token = jwtTokenUtil.generateToken(user);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization Token", token);
        return new ResponseEntity<String>(responseHeaders, HttpStatus.OK);
    }

    @PostMapping("/api/decodeToken")
    public Claims decodeJwtToken(@RequestBody String jwtToken){
       return jwtTokenUtil.getClaimFromToken(jwtToken, DefaultClaims::new);

    }

}
