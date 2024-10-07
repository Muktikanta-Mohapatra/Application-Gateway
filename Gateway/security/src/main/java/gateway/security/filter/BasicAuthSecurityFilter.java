package gateway.security.filter;

import gateway.security.validator.AuthHeaderValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class BasicAuthSecurityFilter extends GatewaySecurityFilter {
    private final Logger LOGGER = LoggerFactory.getLogger(BasicAuthSecurityFilter.class);
    private final AuthHeaderValidator<UsernamePasswordAuthenticationToken> authHeaderValidatorAndTokenGenerator;
    private final AuthenticationManager authenticationManager;

    public BasicAuthSecurityFilter(AuthHeaderValidator<UsernamePasswordAuthenticationToken> authHeaderValidatorAndTokenGenerator, AuthenticationManager authenticationManager) {
        this.authHeaderValidatorAndTokenGenerator = authHeaderValidatorAndTokenGenerator;
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void authenticateAndAuthorize(String authorizationToken, String authVersion, boolean isDefault) {
        if (Objects.equals(authVersion, "1") || isDefault) {
            LOGGER.info("Basic Authentication mechanism selected , x-auth-version : {} , isDefault: {}", authVersion, isDefault);
            String credentials = authorizationToken.substring("Basic ".length()).trim();
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = authHeaderValidatorAndTokenGenerator.authHeaderValidatorAndTokenGenerator(credentials);
            Authentication authResult = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            if (authResult != null)
                SecurityContextHolder.getContext().setAuthentication(authResult);
        }
    }
}
