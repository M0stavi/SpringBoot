package com.mahin.restful.restfulApi.controller;

import com.mahin.restful.restfulApi.entity.HelloWorldBean;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloWorld {

    private MessageSource messageSource;

    public HelloWorld(MessageSource messageSource){
        this.messageSource = messageSource;
    }

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

    @GetMapping(path = "/hello-world-international")
    public String helloWorldInternational(){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default", locale);
    }
}
