package com.mahin.restful.restfulApi.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    @GetMapping("/v1/person")
    public PersonV1 getPersonV1(){
        return new PersonV1("Bob charlie");
    }

    @GetMapping("/v2/person")
    public PersonV2 getPersonV2(){
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getPersonParamsV1(){
        return new PersonV1("Bob charlie");
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getPersonParamV2(){
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    @GetMapping(path = "/person", headers = "X-API-VERSION=1")
    public PersonV1 getPersonHeaderV1(){
        return new PersonV1("Bob charlie");
    }

    @GetMapping(path = "/person", headers = "X-API-VERSION=2")
    public PersonV2 getPersonHeaderV2(){
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    @GetMapping(path = "/person", produces = "application/person.v1.app+json")
    public PersonV1 getPersonMediaV1(){
        return new PersonV1("Bob charlie");
    }

    @GetMapping(path = "/person", produces = "application/person.v2.app+json")
    public PersonV2 getPersonMediaV2(){
        return new PersonV2(new Name("Bob", "Charlie"));
    }


}
