package com.blackcrowsys.dinewell.hotel.security.controller;

import com.blackcrowsys.dinewell.hotel.security.dto.OrganisationDTO;
import com.blackcrowsys.dinewell.hotel.security.service.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/organisation")
@PreAuthorize("hasPermission('SYSTEM', 'READ')")
public class OrganisationController {

    @Autowired
    private OrganisationService organisationService;

    @GetMapping
    public List<OrganisationDTO> getOrganisations() {
        return organisationService.getAll();
    }
}
