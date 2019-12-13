package com.sfg.petclinic.web.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.sfg.petclinic.data.model.PetType;
import com.sfg.petclinic.data.service.PetTypeService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;
    
    @Autowired
    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }

    @Override
    public PetType parse(String name, Locale locale) throws ParseException {
        log.debug("PetTypeFormatter: parse(" + name + ")");
        for(PetType petType : petTypeService.findAll()) {
            if(petType.getName().equals(name)) {
                return petType;
            }
        }
        throw new ParseException("PetType not found", 0);
    }
}
