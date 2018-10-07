package com.blackcrowsys.dinewell.hotel.common.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service("permissionEvaluator")
public class BcsPermissionEvaluator implements PermissionEvaluator {

    private static final Logger LOGGER = LoggerFactory.getLogger(BcsPermissionEvaluator.class);

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        AccessLevel level;
        try {
            level = AccessLevel.valueOf(permission.toString());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid access level [" + permission + "]");
        }
        try {
            BcsUserDetails user = (BcsUserDetails) authentication.getPrincipal();
            return (hasPermission(user, Role.valueOf((String) targetDomainObject), level));
        } catch (ClassCastException e) {
            LOGGER.warn("Casting principal to BcsuserDetails exception: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
                                 Object permission) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public static boolean hasPermission(Role role, AccessLevel level) {
        BcsUserDetails user = (BcsUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return hasPermission(user, role, level);
    }

    private static boolean hasPermission(BcsUserDetails user, Role role, AccessLevel level) {
        BcsGrantedAuthority authority = user.findAuthority(role.name());
        if (authority == null) {
            return false;
        }
        return authority.getAccessLevel().compareTo(level) >= 0;
    }

}
