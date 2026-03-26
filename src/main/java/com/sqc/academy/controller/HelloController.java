package com.sqc.academy.controller;

import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello") // API, endpoint
    public String greeting(@RequestParam(defaultValue = "") String name,
                           @RequestParam(defaultValue = "") String address) { // /hello?name=Luân&address=Đà Nẵng
        return String.format("Hello %s%s", name, Strings.isEmpty(address) ? "" : " - " + address);
    }
}
