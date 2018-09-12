package com.blackcrowsys.dinewell.hotel.security.repository.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORGANISATION")
@Data
@EqualsAndHashCode(callSuper = false)
public class Organisation extends AbstractUUIDEntity {

    private static final long serialVersionUID = 3603406359133335840L;

    @Column(name = "ORGANISATION_NAME")
    private String name;

    @Column(name = "STREET")
    private String street;

    @Column(name = "TOWN")
    private String town;

    @Column(name = "COUNTY")
    private String county;

    @Column(name = "POSTCODE")
    private String postcode;

    @OneToMany(mappedBy = "organisation", fetch = FetchType.EAGER)
    private List<User> users = new ArrayList<>();

}
