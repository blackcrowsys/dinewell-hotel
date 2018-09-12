package com.blackcrowsys.dinewell.hotel.security.repository.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ROLE_ACCESS")
@Data
public class RoleAccess extends AbstractUUIDEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private Role role;

    @Column(name = "ACCESS")
    private char access;
}
