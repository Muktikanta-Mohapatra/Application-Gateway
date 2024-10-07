package gateway.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public abstract class GatewaySecurityFilter extends OncePerRequestFilter {
    private final Logger LOGGER = LoggerFactory.getLogger(GatewaySecurityFilter.class);
    private boolean isDefault = false;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authVersion = request.getHeader("x-auth-version");
        LOGGER.info("Gateway authentication filter intercepted . Requested authentication version : {}", authVersion);
        if (authVersion == null || authVersion.isBlank()) {
            LOGGER.info("Gateway authentication filter intercepted . No authentication header value provided, selected authentication mechanism : {} , isDefaultEnabled : {} ", authVersion, isDefault);
            isDefault = true;
        }
        String authHeader = request.getHeader("Authorization");
        authenticateAndAuthorize(authHeader, authVersion, isDefault);
        filterChain.doFilter(request, response);
    }

    protected abstract void authenticateAndAuthorize(String authorizationToken, String authVersion, boolean isDefault);

}
