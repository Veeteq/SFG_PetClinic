package com.sfg.petclinic.web.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriTemplate;

import com.sfg.petclinic.data.model.Owner;
import com.sfg.petclinic.data.model.Pet;
import com.sfg.petclinic.data.model.PetType;
import com.sfg.petclinic.data.service.PetService;
import com.sfg.petclinic.data.service.VisitService;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(MockitoExtension.class)
@Slf4j
class VisitControllerTest {

    private static final String VIEWS_VISIT_ADD_OR_UPDATE = "visits/addOrUpdate";
    private static final String REDIRECT_OWNERS_1 = "redirect:/owners/1";
    
    @Mock
    VisitService visitService;
    
    @Mock
    PetService petService;
    
    @InjectMocks
    VisitController visitController;
    
    private MockMvc mockMvc;
    
    private final UriTemplate visitsUriTemplate = new UriTemplate("/owners/{ownerId}/pets/{petId}/visits/new");
    private final Map<String, String> uriVariables = new HashMap<>();
    private URI visitsUri;
    
    private Long ownerId1 = 1L;
    private Long petId2 = 2L;
    
    @BeforeEach
    void setUp() throws Exception {
        Owner owner = Owner.builder()
                .id(ownerId1)
                .firstName("Mark")
                .lastName("Hamil")
                .build();
        Pet pet = Pet.builder()
                .id(petId2)
                .name("Bilbo").birthDate(LocalDate.of(2017, 10, 25))
                .petType(PetType.builder().name("Dog").build())
                .visits(new HashSet<>())
                .owner(owner)
                .build();
        
        when(petService.findById(anyLong())).thenReturn(pet);
        
        uriVariables.clear();
        uriVariables.put("ownerId", ownerId1.toString());
        uriVariables.put("petId", petId2.toString());
        visitsUri = visitsUriTemplate.expand(uriVariables);
        
        log.debug("visitsUri: " + visitsUri.toString());
        
        mockMvc = MockMvcBuilders
                .standaloneSetup(visitController)
                .build();
    }

    @Test
    void testNewVisitFormShow() throws Exception {
        mockMvc.perform(get(visitsUri))
        .andExpect(status().isOk())
        .andExpect(view().name(VIEWS_VISIT_ADD_OR_UPDATE));
    }

    @Test
    void testNewVisitFormPost() throws Exception {
        mockMvc.perform(post(visitsUri)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                //.param("date","2018-11-11"))
                .param("description", "test visit"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name(REDIRECT_OWNERS_1))
        .andExpect(model().attributeExists("visit"));
    }
}
