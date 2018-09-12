package com.blackcrowsys.dinewell.hotel.security.controller;

import com.blackcrowsys.dinewell.hotel.security.repository.model.Organisation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/organisation")
@PreAuthorize("hasPermission('SYSTEM', 'READ')")
public class OrganisationController {

    @GetMapping
    public List<Organisation> getOrganisations(){
        Organisation o1 = new Organisation();
        o1.setName("Test");
        return Arrays.asList(o1);
    }
}
