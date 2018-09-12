package com.blackcrowsys.dinewell.hotel.common.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public interface BcsUserDetails extends UserDetails {

    BcsGrantedAuthority findAuthority(String role);

    String getUserId();

    Collection<? extends GrantedAuthority> getAuthorities();
}
