package com.example.highweb5back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class testController {
    @GetMapping("/get")
    public ResponseEntity<String> testGet(){
        return ResponseEntity.ok("정상 동작");
    }
}
