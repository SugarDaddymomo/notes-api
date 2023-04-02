package com.ashutosh.notes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notes-api")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("hello secured endpoint!");
    }
}