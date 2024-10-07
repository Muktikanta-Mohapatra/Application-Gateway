package gateway.security.validator;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthHeaderValidator<T> {
    T authHeaderValidatorAndTokenGenerator(String token);
}
