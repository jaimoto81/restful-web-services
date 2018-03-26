package com.jaimoto.rest.webservices.restfulwebservices.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonaVersionControl {

    @GetMapping("/v1/person")
    public PersonV1 personV1(){
        return new PersonV1("Happy Lora");
    }

    @GetMapping("/v2/person")
    public PersonV2 personV2(){
        return new PersonV2(new Name("Happy", "Lora"));
    }


    @GetMapping(value = "/person/header",headers = "X-API-VERSION=1")
    public PersonV1 personV1Header(){
        return new PersonV1("Happy Lora");
    }

    @GetMapping(value = "/person/header",headers = "X-API-VERSION=2")
    public PersonV2 personV2Header(){
        return new PersonV2(new Name("Happy", "Lora"));
    }

    @GetMapping(value = "/person/produces",produces = "application/vnd.company.app-v1+json")
    public PersonV1 personV1Prod(){
        return new PersonV1("Happy Lora");
    }

    @GetMapping(value = "/person/produces",produces = "application/vnd.company.app-v2+json")
    public PersonV2 personV2Prod(){
        return new PersonV2(new Name("Happy", "Lora"));
    }
}
