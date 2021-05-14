package ecommerce.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HomeController {
    @Secured("ROLE_READ")
    @RequestMapping(value = "/user", method = {RequestMethod.GET, RequestMethod.PUT})
    public String user() {
        return "Hello User";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/admin", method = {RequestMethod.GET, RequestMethod.PUT})
    public String admin() {
        return "Hello Admin";
    }

    @Secured({"ROLE_USER,ROLE_ADMIN"})
    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.PUT})
    public String home() {
        return "Home Page";
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/api/test", method = {RequestMethod.GET, RequestMethod.POST})
    public String testEndpoint() {
        return "You are authenticated";
    }

}