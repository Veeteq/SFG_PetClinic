package com.sfg.petclinic.data.service.map;

import org.springframework.stereotype.Service;

import com.sfg.petclinic.data.model.PetType;
import com.sfg.petclinic.data.service.PetTypeService;

@Service
public class PetTypeServiceMap extends AbstractMapService<PetType, Long> implements PetTypeService {

}
