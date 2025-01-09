package com.mahin.restful.restfulApi.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

//    @GetMapping("/filter")
//    public SomeBean getBean(){
//        return new SomeBean("v1", "v2", "v3");
//    }
//
//    @GetMapping("/filter-list")
//    public List<SomeBean> getBeanList(){
//        return Arrays.asList(new SomeBean("v1", "v2", "v3"),
//                new SomeBean("v4", "v5", "v6"));
//    }

    @GetMapping("/filter")
    public MappingJacksonValue getBean(){
        SomeBean someBean = new SomeBean("v1", "v2", "v3");
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
        SimpleBeanPropertyFilter filter =
                SimpleBeanPropertyFilter.serializeAllExcept("field2");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SimpleBeanFilter", filter);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }

    @GetMapping("/filter-list")
    public MappingJacksonValue getBeanList(){
        List<SomeBean> list = new ArrayList<>();
        list.add(new SomeBean("v1", "v2", "v3"));
        list.add(new SomeBean("v4", "v5", "v6"));

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);

        SimpleBeanPropertyFilter filter =
                SimpleBeanPropertyFilter.serializeAllExcept("field1", "field3");

        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SimpleBeanFilter", filter);

        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }

}
