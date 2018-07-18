package mwisnie.project.airqualitymonitorbackend.secutiry.filter;

import io.jsonwebtoken.Jwts;
import mwisnie.project.airqualitymonitorbackend.secutiry.SecurityConfiguration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class TokenAuthenticationFilter extends BasicAuthenticationFilter {

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
                                    throws IOException, ServletException {
        System.out.println("\n\nTOKEN AUTHENTICATION");
        String authenticationHeader = request.getHeader(SecurityConfiguration.AUTHORIZATION_HEADER);

        if (authenticationHeader == null || !authenticationHeader.startsWith(SecurityConfiguration.AUTHORIZATION_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationTokenFromRequest(request);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationTokenFromRequest(HttpServletRequest request) {
        String authenticationHeader = request.getHeader(SecurityConfiguration.AUTHORIZATION_HEADER);

        if (authenticationHeader != null) {
            String userName = Jwts.parser().setSigningKey(SecurityConfiguration.JWT_SECRET.getBytes())
                    .parseClaimsJws(authenticationHeader.replace(SecurityConfiguration.AUTHORIZATION_PREFIX, ""))
                    .getBody().getSubject();

            if (userName != null) {
                return new UsernamePasswordAuthenticationToken(userName, null, new ArrayList());
            }
        }
        return null;
    }

}
