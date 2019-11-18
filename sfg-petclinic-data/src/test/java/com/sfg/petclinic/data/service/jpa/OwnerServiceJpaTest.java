package com.sfg.petclinic.data.service.jpa;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sfg.petclinic.data.model.Owner;
import com.sfg.petclinic.data.repository.OwnerRepository;

@ExtendWith(MockitoExtension.class)
class OwnerServiceJpaTest {

    @Mock
    private OwnerRepository ownerRepository;
    
    @InjectMocks
    private OwnerServiceJpa ownerService;

    private Long owner1Id = 1L;
    private Long owner2Id = 2L;
    private String lastName1 = "Jackson";
    private String lastName2 = "Duncan";
    
    private Owner owner1;
    private Owner owner2;
    
    @BeforeEach
    void setUp() throws Exception {
        //given
        owner1 = Owner.builder().id(owner1Id).lastName(lastName1).build();
        owner2 = Owner.builder().id(owner2Id).lastName(lastName2).build();
    }

    @Test
    void testFindAll() {
        Set<Owner> owners = new HashSet<>();
        owners.add(owner1);
        owners.add(owner2);
        
        //when
        Mockito.when(ownerRepository.findAll()).thenReturn(owners);
        
        //then
        Set<Owner> retOwners = ownerService.findAll();
        
        assertNotNull(retOwners);
        assertEquals(2, retOwners.size());
        Mockito.verify(ownerRepository).findAll();
    }

    @Test
    void testFindById() {
        //when
        Mockito.when(ownerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(owner1));

        //then
        Owner retOwner = ownerService.findById(owner1Id);

        assertNotNull(retOwner);
        Mockito.verify(ownerRepository).findById(Mockito.anyLong());
        
    }

    @Test
    void testFindByIdNotFound() {
        //when
        Mockito.when(ownerRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        //then
        Owner retOwner = ownerService.findById(owner1Id);

        assertNull(retOwner);
        Mockito.verify(ownerRepository).findById(Mockito.anyLong());
    }

    @Test
    void testSave() {
        //when
        Mockito.when(ownerRepository.save(Mockito.any())).thenReturn(owner1);
        
        //then
        Owner retOwner = ownerService.save(owner1);
        
        assertNotNull(retOwner);
        assertEquals(owner1Id, retOwner.getId());
        assertEquals(lastName1, retOwner.getLastName());

        Mockito.verify(ownerRepository).save(Mockito.any());
    }

    @Test
    void testDelete() {
        ownerService.delete(owner1);
        Mockito.verify(ownerRepository, Mockito.times(1)).delete(Mockito.any());
    }

    @Test
    void testDeleteById() {
        ownerService.deleteById(owner1Id);
        Mockito.verify(ownerRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }

    @Test
    void testFindByLastName() {
        //when
        Mockito.when(ownerRepository.findByLastName(Mockito.any())).thenReturn(owner1);
        
        //then
        Owner retOwner = ownerService.findByLastName(lastName1);
        
        assertEquals(owner1Id, retOwner.getId());
        assertEquals(lastName1, retOwner.getLastName());
        
        Mockito.verify(ownerRepository).findByLastName(Mockito.any());
    }
}
