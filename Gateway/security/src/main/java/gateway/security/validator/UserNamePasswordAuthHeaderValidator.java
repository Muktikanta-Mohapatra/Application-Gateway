package gateway.security.validator;

import gateway.security.validator.helper.AuthenticationTokenExtractor;
import gateway.security.validator.helper.BasicUserNamePasswordAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserNamePasswordAuthHeaderValidator implements AuthHeaderValidator<UsernamePasswordAuthenticationToken> {
    @Autowired
    private AuthenticationTokenExtractor<UsernamePasswordAuthenticationToken, Map<String, String>> tokenExtractor=new BasicUserNamePasswordAuthenticationToken();

    @Override
    public UsernamePasswordAuthenticationToken authHeaderValidatorAndTokenGenerator(String token) {
        return tokenExtractor.setAuthenticationTokenWithTokenWrapper(token);
    }
}
