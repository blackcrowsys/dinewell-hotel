package com.blackcrowsys.dinewell.hotel.common.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class JwtSecurityCachingService implements CachingService<String, String, UserDetails> {

    private static final String JWT_MAP = "jwtMap";
    private static final String USER_MAP = "userMap";

    @Autowired
    private HazelcastInstance instance;

    private IMap<String, UserDetails> jwtCache;
    private IMap<String, String> userMap;

    @PostConstruct
    public void init() {
        jwtCache = instance.getMap(JWT_MAP);
        userMap = instance.getMap(USER_MAP);
    }

    @Override
    public void put(String jwtToken, UserDetails userDetails) {
        jwtCache.put(jwtToken, userDetails);
        userMap.put(userDetails.getUsername(), jwtToken);
    }

    @Override
    public Option<UserDetails> get(String jwtToken) {
        if (jwtCache.containsKey(jwtToken)) {
            return Option.of(jwtCache.get(jwtToken));
        }
        return Option.none();
    }

    @Override
    public Option<String> getKey(String username) {
        if (userMap.containsKey(username)) {
            return Option.of(userMap.get(username));
        }
        return Option.none();
    }
}
