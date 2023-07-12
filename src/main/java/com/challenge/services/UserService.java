package com.challenge.services;

import com.challenge.dto.UserDTO;
import com.challenge.entities.User;

import java.util.List;

public interface UserService {
    UserDTO save(User body);
    UserDTO update(UserDTO body, int userId);
    UserDTO delete(int id);
    UserDTO findById(int userId);
    List<UserDTO> getAll();

}
