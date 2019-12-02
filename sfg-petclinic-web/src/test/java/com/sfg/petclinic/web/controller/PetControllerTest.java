package com.sfg.petclinic.web.controller;

import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.sfg.petclinic.data.model.Owner;
import com.sfg.petclinic.data.model.Pet;
import com.sfg.petclinic.data.model.PetType;
import com.sfg.petclinic.data.service.OwnerService;
import com.sfg.petclinic.data.service.PetService;
import com.sfg.petclinic.data.service.PetTypeService;

@ExtendWith(MockitoExtension.class)
public class PetControllerTest {

    @Mock
    private OwnerService ownerService;
    
    @Mock
    private PetService petService;

    @Mock
    private PetTypeService petTypeService;

    @InjectMocks
    private PetController petController;

    private MockMvc mockMvc;
    
    private Owner owner1;
    private Long ownerId1 = 1L;

    private Set<PetType> petTypes;
    
    @BeforeEach
    void setUp() {
        owner1 = Owner.builder().id(ownerId1).lastName("Ford").build();
        
        petTypes = new HashSet<>();
        petTypes.add(PetType.builder().id(1L).name("Dog").build());
        petTypes.add(PetType.builder().id(2L).name("Cat").build());
        
        mockMvc = MockMvcBuilders
                .standaloneSetup(petController)
                .build();
    }
    
    @Test
    void testNewPetFormShow() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner1);
        when(petTypeService.findAll()).thenReturn(petTypes);
        
        mockMvc.perform(get("/owners/1/pets/new"))
        .andExpect(status().isOk())
        .andExpect(view().name("pets/addOrUpdate"))
        .andExpect(model().attributeExists("owner"))
        .andExpect(model().attributeExists("pet"))
        .andExpect(model().attributeExists("petTypes"))
        .andExpect(model().attribute("owner", instanceOf(Owner.class)));
        
        verifyZeroInteractions(petService);
    }

    @Test
    void testNewPetFormPost() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner1);
        when(petTypeService.findAll()).thenReturn(petTypes);
        
        mockMvc.perform(post("/owners/1/pets/new"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/owners/1"))        
        .andExpect(model().attributeExists("owner"))
        .andExpect(model().attributeExists("pet"))
        .andExpect(model().attributeExists("petTypes"))
        .andExpect(model().attribute("owner", instanceOf(Owner.class)));
        
        verify(petService, times(1)).save(any(Pet.class));
    }
    
    @Test
    void testEditPetFormShow() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner1);
        when(petTypeService.findAll()).thenReturn(petTypes);
        when(petService.findById(anyLong())).thenReturn(Pet.builder().id(3L).build());
        
        mockMvc.perform(get("/owners/1/pets/1/edit"))
        .andExpect(status().isOk())
        .andExpect(view().name("pets/addOrUpdate"))
        .andExpect(model().attributeExists("owner"))
        .andExpect(model().attributeExists("pet"))
        .andExpect(model().attributeExists("petTypes"))
        .andExpect(model().attribute("owner", instanceOf(Owner.class)));
        
        verifyZeroInteractions(petService);
    }    
    
    @Test
    void testEditPetFormPost() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner1);
        when(petTypeService.findAll()).thenReturn(petTypes);
        
        mockMvc.perform(post("/owners/1/pets/3/edit"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/owners/1"))        
        .andExpect(model().attributeExists("owner"))
        .andExpect(model().attributeExists("pet"))
        .andExpect(model().attributeExists("petTypes"))
        .andExpect(model().attribute("owner", instanceOf(Owner.class)));
        
        verify(petService, times(1)).save(any(Pet.class));
    }    
}
