package com.example.CrudPB.controllers;

import com.example.CrudPB.dto.request.UserRequestDTO;
import com.example.CrudPB.dto.response.UserResponseDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.CrudPB.service.ISessionService;

import javax.validation.Valid;

@RequestMapping(path = "/CrudPB")
@RestController
public class SessionController {

    private final ISessionService service;

    public SessionController(ISessionService sessionService) {
        this.service = sessionService;
    }

    @PostMapping("/login")
    public UserResponseDTO login(@Valid @RequestBody UserRequestDTO user ) {
        return service.login(user);
    }


}
