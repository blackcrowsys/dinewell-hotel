package com.blackcrowsys.dinewell.hotel.security.service;

import com.blackcrowsys.dinewell.hotel.common.security.UserPrincipal;
import com.blackcrowsys.dinewell.hotel.common.service.JwtSecurityCachingService;
import com.blackcrowsys.dinewell.hotel.common.service.JwtSecurityTokeniser;
import com.blackcrowsys.dinewell.hotel.security.dto.LoginForm;
import com.blackcrowsys.dinewell.hotel.security.repository.dao.LoginRepository;
import com.blackcrowsys.dinewell.hotel.security.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticationService {

    @Autowired
    private LoginRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtSecurityTokeniser jwtTokeniser;

    @Autowired
    private JwtSecurityCachingService cachingService;

    public ResponseEntity<String> authenticate(LoginForm form) {
        Optional<User> user = repository.findByUsername(form.getUsername());
        ResponseEntity<String> response=  user.map(u -> checkUser(form, u)).orElseGet(() -> new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
        if(response.getStatusCode().is2xxSuccessful()){
            cacheUser(response.getBody(), UserPrincipal.create(user.get()));
        }
        return response;
    }

    private void cacheUser(String jwtToken, UserDetails userDetails) {
        cachingService.put(jwtToken, userDetails);
    }

    private ResponseEntity<String> checkUser(final LoginForm form, final User user) {
        if (!encoder.matches(form.getPassword(), user.getPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (user.isAccountNonExpired() && user.isAccountNonLocked() && user.isCredentialsNonExpired()) {
            String token = jwtTokeniser.tokenise(user);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
