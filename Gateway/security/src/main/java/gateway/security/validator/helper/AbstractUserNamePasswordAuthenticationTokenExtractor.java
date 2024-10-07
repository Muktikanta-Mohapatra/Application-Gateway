package gateway.security.validator.helper;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.Map;

public abstract class AbstractUserNamePasswordAuthenticationTokenExtractor implements AuthenticationTokenExtractor<UsernamePasswordAuthenticationToken, Map<String, String>> {
    @Override
    public UsernamePasswordAuthenticationToken setAuthenticationTokenWithTokenWrapper(String token) {
        if (token == null || token.isBlank()) {
            throw new RuntimeException();
        }
        Map<String, String> credentialMap = extractAuthCredential(token);
        return new UsernamePasswordAuthenticationToken(credentialMap.get("userName"), credentialMap.get("password"), Collections.singletonList(new SimpleGrantedAuthority("ROLE")));
    }

    @Override
    public abstract Map<String, String> extractAuthCredential(String token);

}
