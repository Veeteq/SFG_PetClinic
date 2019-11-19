package com.sfg.petclinic.data.service.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.sfg.petclinic.data.model.Income;
import com.sfg.petclinic.data.service.IncomeService;

@Service
@Profile(value = {"default","map"})
public class IncomeServiceMap extends AbstractMapService<Income, Long> implements IncomeService {

}
