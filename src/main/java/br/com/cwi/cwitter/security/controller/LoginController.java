package br.com.cwi.cwitter.security.controller;

import br.com.cwi.cwitter.security.controller.response.UserResponse;
import br.com.cwi.cwitter.security.service.AuthenticatedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticatedUserService service;

    @PostMapping
    public UserResponse login() {
        return service.getResponse();
    }
}
