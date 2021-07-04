package ecommerce.controller;

import ecommerce.entity.SecureUser;
import ecommerce.entity.WebRequest;
import ecommerce.exceptions.EmailAlreayPresentException;
import ecommerce.exceptions.UserAlreadyPresentException;
import ecommerce.services.UserService;
import ecommerce.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@ResponseBody
@RequestMapping("/api")
public class LoginController {

    @Autowired
    UserService userService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<SecureUser> login(@RequestBody final WebRequest webRequest){
        SecureUser user = (SecureUser) userService.authenticateUser(webRequest);
        if(user == null)
            return ResponseEntity.notFound().build();
        user.erasePassword();
        String token = jwtTokenUtil.generateToken(user);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(HttpHeaders.AUTHORIZATION, token);
        responseHeaders.add("Access-Control-Expose-Headers", "Authorization");
        return ResponseEntity.ok().headers(responseHeaders).body(user);
    }

    @PostMapping(value = "/signup",consumes = "application/json")
    public ResponseEntity<String> signup(@RequestBody final WebRequest account) throws EmailAlreayPresentException, UserAlreadyPresentException {
        userService.addAccount(account);
        return ResponseEntity.ok("You're Account has been created..Please login to use the application");
    }
}
