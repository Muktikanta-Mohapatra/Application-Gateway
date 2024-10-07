package gateway.security.validator.helper;

public interface AuthenticationTokenExtractor<W, T> {
    public W setAuthenticationTokenWithTokenWrapper(String token);

    public T extractAuthCredential(String token);
}
