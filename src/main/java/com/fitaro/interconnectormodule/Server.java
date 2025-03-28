package com.fitaro.interconnectormodule;

import org.springframework.web.bind.annotation.*;

@RestController
public class Server {
    @GetMapping("/")
    public String addProduct() {
        return "Server is running";
    }
}
