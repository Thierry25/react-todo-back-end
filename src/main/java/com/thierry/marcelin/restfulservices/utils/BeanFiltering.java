package com.thierry.marcelin.restfulservices.utils;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Component;

@Component
public class BeanFiltering {

    public static MappingJacksonValue filter(Object object, String filterName, String... paramsToBeIncluded){
        var mapping = new MappingJacksonValue(object);
        var filter = SimpleBeanPropertyFilter.filterOutAllExcept(paramsToBeIncluded);
        var filters = new SimpleFilterProvider().addFilter(filterName, filter);
        mapping.setFilters(filters);
        return mapping;
    }
}
