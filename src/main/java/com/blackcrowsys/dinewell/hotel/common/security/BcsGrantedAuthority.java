package com.blackcrowsys.dinewell.hotel.common.security;

import com.blackcrowsys.dinewell.hotel.security.repository.model.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class BcsGrantedAuthority implements GrantedAuthority {

    private static final long serialVersionUID = -5909411782438756717L;

    public BcsGrantedAuthority() {
        // Default constructor
    }

    public BcsGrantedAuthority(Role role, AccessLevel accessLevel) {
        this.role = role;
        this.accessLevel = accessLevel;
    }

    private Role role;

    private AccessLevel accessLevel;

    @Override
    public String getAuthority() {
        return accessLevel.toString();
    }
}
