package mwisnie.project.airqualitymonitorbackend.secutiry.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import mwisnie.project.airqualitymonitorbackend.entity.User;
import mwisnie.project.airqualitymonitorbackend.secutiry.SecurityConfiguration;
import mwisnie.project.airqualitymonitorbackend.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class CredentialsAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private UserServiceImpl userService;

    public CredentialsAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        if (userService == null){
            ServletContext servletContext = request.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            userService = webApplicationContext.getBean(UserServiceImpl.class);
        }

        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);

            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>());

            return authenticationManager.authenticate(authenticationToken);

        } catch (IOException e) {
            // throw error
            System.out.println("error in CredentialsAuthenticationFilter; TODO: throw it");
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication auth) {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        String jwtToken = Jwts.builder()
                .setSubject(principal.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConfiguration.TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConfiguration.JWT_SECRET.getBytes())
                .compact();
        String userId = userService.getUserByUsername(principal.getUsername()).getId();

        response.addHeader(SecurityConfiguration.AUTHORIZATION_HEADER, SecurityConfiguration.AUTHORIZATION_PREFIX + jwtToken);
        response.addHeader("UserId", userId);
    }

}
