package ecommerce.controller;

import ecommerce.entity.WebRequest;
import ecommerce.services.UserService;
import ecommerce.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@CrossOrigin
@RestController
@ResponseBody
public class LoginController {
 
  @Autowired
  UserService userService;
  @Autowired
  JwtTokenUtil jwtTokenUtil;


  @PostMapping("/api/login")
  public ResponseEntity<String> login(@RequestBody final WebRequest webRequest){
   UserDetails user = userService.authenticateUser(webRequest);
    String token = jwtTokenUtil.generateToken(user);
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.set("Authorization Token", token);
    return new ResponseEntity<String>(responseHeaders, HttpStatus.OK);
    //ResponseEntity.status(200).build();
  }

  @PostMapping("/api/signup") 
  public String signup(@RequestBody final WebRequest account) throws Exception {
    userService.addAccount(account);
    //ResponseEntity.ok().build();
    return "You're Account has been created..Please login to use the application";
 }

  private final Logger logger = Logger.getLogger(this.getClass().getName());
}
