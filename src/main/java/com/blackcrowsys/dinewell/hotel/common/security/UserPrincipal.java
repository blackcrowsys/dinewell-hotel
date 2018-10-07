package com.blackcrowsys.dinewell.hotel.common.security;

import com.blackcrowsys.dinewell.hotel.security.repository.model.User;
import com.blackcrowsys.dinewell.hotel.security.repository.model.UserType;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class UserPrincipal implements Serializable {

    private static final long serialVersionUID = -4614150925436121638L;

    private String username;

    private String jwtToken;

    private boolean authenticated;

    private UserType type;

    private Map<String, String> permissions;

    public static UserDetails create(final User user) {

        return new BcsUserDetails() {

            @Override
            public BcsGrantedAuthority findAuthority(String role) {
                return user
                        .getRoleAccesses()
                        .stream()
                        .filter(ra -> ra.getRole().name().equalsIgnoreCase(role))
                        .findFirst()
                        .map(ra -> new BcsGrantedAuthority(ra.getRole(), AccessLevel.findAccessLevel(ra.getAccess())))
                        .orElse(new BcsGrantedAuthority());
            }

            @Override
            public String getUserId() {
                return user.getUsername();
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return user.getRoleAccesses()
                        .stream()
                        .map(ra -> new BcsGrantedAuthority(ra.getRole(), AccessLevel.findAccessLevel(ra.getAccess())))
                        .collect(Collectors.toList());
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getUsername();
            }

            @Override
            public boolean isAccountNonExpired() {
                return user.isAccountNonExpired();
            }

            @Override
            public boolean isAccountNonLocked() {
                return user.isAccountNonLocked();
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return user.isCredentialsNonExpired();
            }

            @Override
            public boolean isEnabled() {
                return user.isEnabled();
            }
        };
    }
}
