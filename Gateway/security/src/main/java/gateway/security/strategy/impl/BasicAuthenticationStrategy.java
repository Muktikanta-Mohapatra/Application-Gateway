package gateway.security.strategy.impl;

import gateway.security.strategy.AbstractAuthStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class BasicAuthenticationStrategy extends AbstractAuthStrategy<UsernamePasswordAuthenticationToken> {
    @Autowired
    @Qualifier("userNamePasswordUserDetailsService")
    private UserDetailsService userDetailsService;

    public BasicAuthenticationStrategy(UserDetailsService userDetailsService) {
        super(userDetailsService);
    }

    @Override
    protected UsernamePasswordAuthenticationToken createAuthenticationToken(Authentication authentication, UserDetails userDetails) {
        return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), getAuthorities(userDetails));
    }

    @Override
    protected Collection<? extends GrantedAuthority> getAuthorities(UserDetails userDetails) {
        return List.of();
    }

    @Override
    protected Class<UsernamePasswordAuthenticationToken> getSupportedAuthenticationClass() {
        return UsernamePasswordAuthenticationToken.class;
    }
}
