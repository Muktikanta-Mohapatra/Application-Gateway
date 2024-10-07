package gateway.security.validator.helper;

import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
public class BasicUserNamePasswordAuthenticationToken extends AbstractUserNamePasswordAuthenticationTokenExtractor {
    public BasicUserNamePasswordAuthenticationToken() {
        super();
    }

    @Override
    public Map<String, String> extractAuthCredential(String token) {
        Map<String, String> credentialMap = new HashMap<>();
        String usernameAndPassword = new String(Base64.getDecoder().decode(token));
        String[] userNameAndPasswordArray = usernameAndPassword.split(":");
        credentialMap.put("userName", userNameAndPasswordArray[0]);
        credentialMap.put("password", userNameAndPasswordArray[1]);
        credentialMap.put("role", "ROLE");

        return credentialMap;
    }
}
