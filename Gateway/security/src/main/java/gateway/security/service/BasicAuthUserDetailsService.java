package gateway.security.service;

import gateway.security.entity.UserEntity;
import gateway.security.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component("userNamePasswordUserDetailsService")
public class BasicAuthUserDetailsService implements UserDetailsService {
    private final Logger LOGGER = LoggerFactory.getLogger(BasicAuthUserDetailsService.class);
    private final UserRepository userRepository;

    public BasicAuthUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("Validating credential against database stored credential. UserName : {} ,Password : *****", username);
        UserEntity byUserName = userRepository.getByUserName(username);
        return new User(byUserName.getUserName(), byUserName.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE")));
    }
}
