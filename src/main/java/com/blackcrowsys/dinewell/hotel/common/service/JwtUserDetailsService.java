package com.blackcrowsys.dinewell.hotel.common.service;

import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private JwtSecurityCachingService cachingService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Option<String> jwtToken = cachingService.getKey(username);
        if (jwtToken.isDefined()) {
            return cachingService.get(jwtToken.get()).get();
        }
        throw new UsernameNotFoundException(username);
    }
}
