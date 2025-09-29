package com.spring.verification.springbackendverification.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/helloworld")
public class Helloworld2 {
    @GetMapping
    public String sayHello(){
        return "Hello World";
    }
}
