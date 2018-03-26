package com.jaimoto.rest.webservices.restfulwebservices.filters;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue retrieveSomeBean(){

        SomeBean someBean = new SomeBean("1","2","3");
        //filter in
        MappingJacksonValue mapping = new MappingJacksonValue(someBean);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("SomeBeanFilter",filter);

        mapping.setFilters(filters);

        return mapping;

    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue retrieveListSomeBean(){

        List<SomeBean> list =  Arrays.asList(new SomeBean("1","2","3"), new SomeBean("3","4","5"));

        MappingJacksonValue mapping = new MappingJacksonValue(list);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("SomeBeanFilter",filter);
        mapping.setFilters(filters);

        return mapping;
    }
}
