package ecommerce.filter;

import ecommerce.entity.User;
import ecommerce.repositories.UserRepo;
import ecommerce.util.JwtTokenUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private final JwtTokenUtil jwtTokenUtil;
    @Autowired
    private final UserRepo userRepo;

    public JwtTokenFilter(JwtTokenUtil jwtTokenUtil,
                          UserRepo userRepo) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepo = userRepo;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        // Get authorization header and validate

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(header == null)
            throw new ServletException("Authentication Header Missing..Please use a valid JWT");
        if (header.isEmpty() || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        // Get jwt token and validate
        final String token = header.split(" ")[1].trim();
        if (!jwtTokenUtil.validateToken(token)) {
            chain.doFilter(request, response);
            return;
        }

        // Get user identity and set it on the spring security context
        User user = userRepo
                .findByUsername(jwtTokenUtil.getUsernameFromToken(token))
                .orElse(null);

        logger.info("Got User From Token : " + user.getUsername());
        logger.info("Got Authority From Token : " + Collections.singletonList((GrantedAuthority) user::getAuthority));

        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                user, user.getPassword(),
                Collections.singletonList((GrantedAuthority) user::getAuthority));

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    @Override
    public boolean shouldNotFilter(HttpServletRequest request){
        String[] acceptableEndpoints = {"/api/login","/api/signup","/api/products/getAll","/api/products/getByTitle/.*"};
        List<String> ignorePaths = Arrays.asList(acceptableEndpoints);
        String path = request.getRequestURL().substring(21);
        logger.info("Path for Filter : " + path);
        for(String endpoint: acceptableEndpoints){
            Pattern pattern = Pattern.compile(endpoint);
            Matcher matcher = pattern.matcher(path);
            if(matcher.matches())  return true;
        }
        return false;
    }

    Logger logger = LogManager.getLogger(JwtTokenFilter.class);

}
