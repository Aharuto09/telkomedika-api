package com.telkomedika.telkomedikaapi.controller;

import com.telkomedika.telkomedikaapi.model.request.authRequest;
import com.telkomedika.telkomedikaapi.model.request.registRequest;
import com.telkomedika.telkomedikaapi.model.response.authResponse;
import com.telkomedika.telkomedikaapi.service.accountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    accountService accountservice;

    @PostMapping("/register")
    public ResponseEntity register(
            @RequestBody registRequest request) {
        accountservice.register(request);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/authenticate")
    public ResponseEntity<authResponse> authenticate(
            @RequestBody authRequest request
    ) {
        return ResponseEntity.ok(accountservice.authenticate(request));
    }
}
