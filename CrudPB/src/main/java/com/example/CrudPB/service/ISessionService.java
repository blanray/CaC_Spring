package com.example.CrudPB.service;

import com.example.CrudPB.dto.request.UserRequestDTO;
import com.example.CrudPB.dto.response.UserResponseDTO;

public interface ISessionService {

    UserResponseDTO login( UserRequestDTO user );

}
