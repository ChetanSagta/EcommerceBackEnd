package ecommerce.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HomeController implements ErrorController {
  
  @RequestMapping( value = "/error", method = {RequestMethod.GET, RequestMethod.PUT})
  public String error() {
    return "Error Landing Page";
  }

@Override
public String getErrorPath() {
	return "/error";
}
  
}
