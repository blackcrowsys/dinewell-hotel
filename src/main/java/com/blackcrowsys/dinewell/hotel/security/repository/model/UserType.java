package com.blackcrowsys.dinewell.hotel.security.repository.model;

import java.util.stream.Stream;

public enum UserType {

    STAFF("Staff"), ROOM("Room"), ADMIN("Admin");

    private String type;

    private UserType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public static UserType getUserType(String code) {
        return Stream.of(UserType.values())
                .filter(s -> s.type.equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
