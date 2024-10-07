package gateway.security.config;

import gateway.security.filter.BasicAuthSecurityFilter;
import gateway.security.filter.JwtAuthSecurityFilter;
import gateway.security.strategy.impl.BasicAuthenticationStrategy;
import gateway.security.validator.AuthHeaderValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthHeaderValidator<UsernamePasswordAuthenticationToken> authHeaderValidatorAndTokenGenerator;
    private final BasicAuthenticationStrategy basicAuthenticationStrategy;

    public SecurityConfig(AuthHeaderValidator<UsernamePasswordAuthenticationToken> authHeaderValidatorAndTokenGenerator, BasicAuthenticationStrategy basicAuthenticationStrategy) {
        this.authHeaderValidatorAndTokenGenerator = authHeaderValidatorAndTokenGenerator;
        this.basicAuthenticationStrategy = basicAuthenticationStrategy;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requestRegistry -> requestRegistry.anyRequest().authenticated()).httpBasic(httpSecurityHttpBasicConfigurer -> {
        });
        http.csrf(httpSecurityCsrfConfigurer -> {
        }).cors(httpSecurityCorsConfigurer -> {
        });
        http.addFilterBefore(new BasicAuthSecurityFilter(authHeaderValidatorAndTokenGenerator, new ProviderManager(basicAuthenticationStrategy)), UsernamePasswordAuthenticationFilter.class);
      //  http.addFilterBefore(new JwtAuthSecurityFilter(authHeaderValidatorAndTokenGenerator, new ProviderManager(jwtAuthenticationStrategy)), UsernamePasswordAuthenticationFilter.class);

        // http.userDetailsService(new UserService());
        // http.authenticationProvider(new BasicAuthAuthenticationProvider());
        http.httpBasic(httpSecurityHttpBasicConfigurer -> {
        });
        return http.build();
    }
}
