package com.featherlopez.emitirfactura.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HelloResource {

    @GetMapping("/hola")
    public String hola() {
        return "Hola Don Viejo!";
    }

}
