package com.mahin.restful.restfulApi.controller;

import com.mahin.restful.restfulApi.entity.HelloWorldBean;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorld {

//    @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    @GetMapping(path = "/hello-world")
    public String getHelloWorld(){
        return "Hello world!!";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean getHelloWorldBean(){
        return new HelloWorldBean("hello world");
    }

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean getPathVariable(@PathVariable String name){
        return new HelloWorldBean("Hello, "+name);
    }
}
