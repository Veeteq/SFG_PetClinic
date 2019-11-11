package com.sfg.petclinic.data.service.map;

import org.springframework.stereotype.Service;

import com.sfg.petclinic.data.model.Speciality;
import com.sfg.petclinic.data.service.SpecialityService;

@Service
public class SpecialityServiceMap extends AbstractMapService<Speciality, Long> implements SpecialityService {

}
