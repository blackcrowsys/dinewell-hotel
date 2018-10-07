package com.blackcrowsys.dinewell.hotel.common.security;

import java.util.Arrays;

public enum AccessLevel {
    NOACCESS('X'), READ('R'), EDIT('E'), ADD('A'), DELETE('D');

    private final char code;

    private String description;

    private AccessLevel(char code) {
        this.code = code;
        switch (code) {
            case 'X':
                this.description = "No Access";
                break;
            case 'R':
                this.description = "Read Only";
                break;
            case 'E':
                this.description = "Edit Records";
                break;
            case 'A':
                this.description = "Add New Records";
                break;
            case 'D':
                this.description = "Delete Records";
                break;
            default:
                throw new IllegalArgumentException("Failed to find description for access level with code [" + code + "]");
        }
    }

    public char getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static AccessLevel findAccessLevel(char code) {
        for (AccessLevel level : AccessLevel.values()) {
            if (level.code == code) {
                return level;
            }
        }
        throw new IllegalArgumentException("Failed to find access level with code [" + code + "]");
    }

    public static AccessLevel findAccessLevel(String desc) {
        return Arrays.stream(AccessLevel.values()).filter(e -> e.description.equals(desc)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Unsupported type: %s", desc)));
    }
}
