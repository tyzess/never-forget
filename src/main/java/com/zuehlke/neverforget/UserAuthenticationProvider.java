package com.zuehlke.neverforget;

import com.zuehlke.neverforget.domain.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
    private final static Logger log = LoggerFactory.getLogger(FakeUserDetailsService.class);

    @Autowired
    UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = userRepository.findByUsernameEquals(authentication.getName());
        String password = (String) authentication.getCredentials();

        if (user == null || !password.equals(user.getPassword())) {
            throw new BadCredentialsException("Incorrect login: User not found or password incorrect.");
        }

        return new UsernamePasswordAuthenticationToken(user, password, getAuthorities(user));
    }

    private List<GrantedAuthority> getAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
