package mwisnie.project.airqualitymonitorbackend.secutiry;

import lombok.AllArgsConstructor;
import mwisnie.project.airqualitymonitorbackend.secutiry.filter.CredentialsAuthenticationFilter;
import mwisnie.project.airqualitymonitorbackend.secutiry.filter.TokenAuthenticationFilter;
import mwisnie.project.airqualitymonitorbackend.service.security.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static final String REGISTRATION_URL = "/api/users";
    public static final String REGISTRATION_CONFIRMATION_URL = "/confirmRegistration";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTHORIZATION_PREFIX = "Bearer ";
    public static final long TOKEN_EXPIRATION_TIME = (long) 1000 * 60 * 60 * 5;

    public static String JWT_SECRET;

    @Autowired
    private final UserDetailServiceImpl userDetailService;

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${secret}")
    public void setJwtSecret(String secret) {
        this.JWT_SECRET = secret;
    }

    @Override
    public void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
        authBuilder.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, REGISTRATION_URL).permitAll()
                .antMatchers(HttpMethod.GET, REGISTRATION_CONFIRMATION_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new CredentialsAuthenticationFilter(authenticationManager()))
                .addFilter(new TokenAuthenticationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource CORS_SOURCE = new UrlBasedCorsConfigurationSource();
        CORS_SOURCE.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());

        //requests from any source permitted
        return CORS_SOURCE;
    }

}
