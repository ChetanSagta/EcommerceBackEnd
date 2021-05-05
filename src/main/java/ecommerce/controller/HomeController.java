package ecommerce.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HomeController {
  
  @RequestMapping( value = "/user", method = {RequestMethod.GET, RequestMethod.PUT})
  public String user() {
    return "Hello User";
  }

  @RequestMapping( value = "/admin", method = {RequestMethod.GET, RequestMethod.PUT})
  public String admin() {
    return "Hello Admin";
  }

  @RequestMapping( value = "/", method = {RequestMethod.GET, RequestMethod.PUT})
  public String home() {
    return "Home Page";
  }

  @RequestMapping(value = "/api/test", method = {RequestMethod.GET,RequestMethod.POST})
  public String testEndpoint(){
    return "You are authenticated";
  }

}
