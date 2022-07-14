package org.exeplm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoContrller {

    @GetMapping
    public String getDemo(){
        System.out.println("users demo");
        return "users demo";
    }

}
