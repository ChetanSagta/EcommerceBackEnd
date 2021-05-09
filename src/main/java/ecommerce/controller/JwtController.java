package ecommerce.controller;

import ecommerce.entity.jwt.JwtPayload;
import ecommerce.services.JwtTokenService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {
    @Autowired
    JwtTokenService jwtTokenService;
    @PostMapping("/api/encodeToken")
    public String getJwtToken(@RequestBody JwtPayload payload){
        return jwtTokenService.createJWT(payload);
    }

    @PostMapping("/api/decodeToken")
    public Claims decodeJwtToken(@RequestBody String jwtToken){
       return jwtTokenService.decodeJWT(jwtToken);

    }

}
