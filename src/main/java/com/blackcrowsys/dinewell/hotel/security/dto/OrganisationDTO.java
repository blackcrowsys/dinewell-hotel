package com.blackcrowsys.dinewell.hotel.security.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrganisationDTO implements Serializable {

    private static final long serialVersionUID = 3305780453336247188L;

    private String name;

    private String street;

    private String town;

    private String county;

    private String postcode;
}
