package ecommerce.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import ecommerce.entity.SecureUser;
import ecommerce.entity.WebRequest;
import ecommerce.services.UserService;
import ecommerce.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@CrossOrigin
@RestController
@ResponseBody
public class LoginController {

    private final Logger logger = Logger.getLogger(this.getClass().getName());
    @Autowired
    UserService userService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/api/login")
    public ResponseEntity<SecureUser> login(@RequestBody final WebRequest webRequest) throws JsonProcessingException {
        SecureUser user = (SecureUser) userService.authenticateUser(webRequest);
        user.erasePassword();
        if(user == null)
            return ResponseEntity.notFound().build();
        String token = jwtTokenUtil.generateToken(user);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(HttpHeaders.AUTHORIZATION, token);
        responseHeaders.add("Access-Control-Expose-Headers", "Authorization");
        return ResponseEntity.ok().headers(responseHeaders).body(user);
    }

    @PostMapping(value = "/api/signup",consumes = "application/json")
    public ResponseEntity<String> signup(@RequestBody final WebRequest account) throws Exception {
        userService.addAccount(account);
        return ResponseEntity.ok("You're Account has been created..Please login to use the application");
    }
}
