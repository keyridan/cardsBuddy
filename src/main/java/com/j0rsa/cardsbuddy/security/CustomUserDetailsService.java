package com.j0rsa.cardsbuddy.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author red
 * @since 06.04.18
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserPrincipal loadUserByUsername(String userName) throws UsernameNotFoundException {
        return new UserPrincipal(userName);
    }
}
