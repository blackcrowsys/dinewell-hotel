package com.blackcrowsys.dinewell.hotel.security.controller;

import com.blackcrowsys.dinewell.hotel.security.dto.OrganisationDTO;
import com.blackcrowsys.dinewell.hotel.security.service.OrganisationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrganisationControllerTests {

    private static final String ORGANISATION_URL = "/api/organisation";

    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    private static final MediaType ACCEPTABLE_MEDIA = new MediaType(
            MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8")
    );

    private static final String HOTEL_NAME = "Hotel Test";

    @Mock
    private OrganisationService organisationService;

    @InjectMocks
    private OrganisationController controller;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        List<OrganisationDTO> dtos = new ArrayList();
        OrganisationDTO dto = new OrganisationDTO();
        dto.setName(HOTEL_NAME);
        dtos.add(dto);
        when(organisationService.getAll()).thenReturn(dtos);
    }

    @Test
    public void testGettingListOfAllHotels() throws Exception {
        mockMvc.perform(get(ORGANISATION_URL).accept(ACCEPTABLE_MEDIA))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(HOTEL_NAME)))
                .andDo(print());
    }
}
