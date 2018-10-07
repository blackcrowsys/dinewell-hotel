package com.blackcrowsys.dinewell.hotel.security.repository.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "APP_USER")
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends AbstractUUIDEntity {

    private static final long serialVersionUID = 1780342589613763500L;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ACCOUNTNONEXPIRED")
    private boolean accountNonExpired;

    @Column(name = "ACCOUNTNONLOCKED")
    private boolean accountNonLocked;

    @Column(name = "CREDENTIALSNONEXPIRED")
    private boolean credentialsNonExpired;

    @Column(name = "ENABLED")
    private boolean enabled;

    @Column(name = "FIRSTNAME")
    private String firstname;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "EMAIL")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "USERTYPE")
    private UserType userType;

    @ManyToOne
    @JoinColumn(name = "ORGANISATION_ID")
    private Organisation organisation;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<RoleAccess> roleAccesses = new HashSet<>();

}
