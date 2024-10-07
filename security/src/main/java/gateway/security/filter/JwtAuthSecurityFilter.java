package gateway.security.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JwtAuthSecurityFilter extends GatewaySecurityFilter {
    private final Logger LOGGER = LoggerFactory.getLogger(JwtAuthSecurityFilter.class);

    @Override
    protected void authenticateAndAuthorize(String authorizationToken, String authVersion, boolean isDefault) {

        if (authVersion.equals("2")) {
            LOGGER.info("JWT Authentication mechanism selected , x-auth-version : {} , isDefault: {}", authVersion, isDefault);
            String credentials = authorizationToken.substring("Bearer ".length()).trim();
        }
    }
}
