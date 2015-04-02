package com.augmentum.fishing.service;

import java.util.List;

import com.augmentum.fishing.dto.UserDTO;

public interface UserService {

    UserDTO getByName(String userName);

    List<UserDTO> findAll();

}
