package com.sfg.petclinic.data.service.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.sfg.petclinic.data.model.Visit;
import com.sfg.petclinic.data.service.VisitService;

@Service
@Profile(value = {"default","map"})
public class VisitServiceMap extends AbstractMapService<Visit, Long> implements VisitService {

    @Override
    public Visit save(Visit visit) {
        if(visit.getPet() == null || visit.getPet().getId() == null || visit.getPet().getOwner() == null) {
            throw new RuntimeException("Invalid data");
        }
        return super.save(visit);
    }
}
