package com.blackcrowsys.dinewell.hotel.common.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordEncoderConfigurationTests {

    private PasswordEncoderConfiguration configuration;

    @BeforeEach
    public void setUp() {
        configuration = new PasswordEncoderConfiguration();
        configuration.init();
        ;
    }

    @Test
    public void testPasswordEncoder() {
        PasswordEncoder encoder = configuration.passwordEncoder();

        assertEquals(DelegatingPasswordEncoder.class, encoder.getClass());
    }
}
