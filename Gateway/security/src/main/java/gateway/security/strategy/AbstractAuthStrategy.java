package gateway.security.strategy;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;

public abstract class AbstractAuthStrategy<A extends Authentication> implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;

    protected AbstractAuthStrategy(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());

        if (isValidPassword(userDetails, authentication.getCredentials().toString())) {
            return createAuthenticationToken(authentication, userDetails);
        } else {
            throw new UsernameNotFoundException("User is not valid");
        }
    }

    protected abstract A createAuthenticationToken(Authentication authentication, UserDetails userDetails);

    protected boolean isValidPassword(UserDetails userDetails, String rawPassword) {
        return userDetails.getPassword().equals(rawPassword);
    }

    protected abstract Collection<? extends GrantedAuthority> getAuthorities(UserDetails userDetails);

    @Override
    public boolean supports(Class<?> authentication) {
        return getSupportedAuthenticationClass().isAssignableFrom(authentication);
    }

    protected abstract Class<A> getSupportedAuthenticationClass();

}
