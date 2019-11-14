package com.sfg.petclinic.data.service.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.sfg.petclinic.data.model.Speciality;
import com.sfg.petclinic.data.service.SpecialityService;

@Service
@Profile(value = {"default","map"})
public class SpecialityServiceMap extends AbstractMapService<Speciality, Long> implements SpecialityService {

}
