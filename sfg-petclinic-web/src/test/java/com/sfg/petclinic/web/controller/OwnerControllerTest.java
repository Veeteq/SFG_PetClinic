package com.sfg.petclinic.web.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.sfg.petclinic.data.model.Owner;
import com.sfg.petclinic.data.service.OwnerService;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    private OwnerService ownerService;
    
    @InjectMocks
    private OwnerController ownerController;
    
    private Owner owner1;
    private Owner owner2;
    private Set<Owner> owners;
    private MockMvc mockMvc;

    private Long ownerId1 = 1L;
    private Long ownerId2 = 2L;
    
    @BeforeEach
    void setUp() throws Exception {
        owner1 = Owner.builder().id(ownerId1).lastName("Ford").build();
        owner2 = Owner.builder().id(ownerId2).lastName("Ford").build();
        
        owners = new HashSet<>();
        owners.add(owner1);
        owners.add(owner2);
        
        mockMvc = MockMvcBuilders
                .standaloneSetup(ownerController)
                .build();
    }

    @Test
    void testGetOwners() throws Exception {
        //when
        when(ownerService.findAll()).thenReturn(owners);
        
        //then
        mockMvc.perform(get("/owners"))
        .andExpect(status().isOk())
        .andExpect(view().name("owners/index"))
        .andExpect(model().attribute("owners", hasSize(2)));
        
        Mockito.verify(ownerService, Mockito.times(1)).findAll();
    }

    @Test
    void testFindOwners() throws Exception {
        //then
        mockMvc.perform(get("/owners/find"))
        .andExpect(status().isOk())
        .andExpect(view().name("notimplemented"));

        Mockito.verifyZeroInteractions(ownerService);
    }
    
    @Test
    void testShowOwners() throws Exception {
        //when
        when(ownerService.findById(anyLong())).thenReturn(owner1);
        
        //then
        mockMvc.perform(get("/owners/2"))
        .andExpect(status().isOk())
        .andExpect(view().name("owners/ownerDetails"))
        .andExpect(model().attribute("owner", hasProperty("id", is(1L))));
        
        Mockito.verify(ownerService, Mockito.times(1)).findById(anyLong());
    }    
}
