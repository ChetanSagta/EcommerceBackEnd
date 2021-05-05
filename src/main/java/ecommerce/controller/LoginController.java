package ecommerce.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import ecommerce.entity.User;
import ecommerce.entity.WebRequest;
import ecommerce.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
@ResponseBody
public class LoginController {
 
  @Autowired
  UserService userService;

  @PostMapping("/api/login")
  public void login(@RequestBody final WebRequest webRequest){
    userService.authenticateUser(webRequest);
    ResponseEntity.status(200).build();
  }

  @PostMapping("/api/signup") 
  public void signup(@RequestBody final WebRequest account) {
    userService.addAccount(account);
    ResponseEntity.ok().build();
 }

//  @RequestMapping(value="/api/logout", method = {RequestMethod.GET, RequestMethod.POST})
//  public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
//    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//    if (auth != null){
//      new SecurityContextLogoutHandler().logout(request, response, auth);
//    }
//    return "redirect:/";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
//  }
  private Logger logger = Logger.getLogger(this.getClass().getName());
}
