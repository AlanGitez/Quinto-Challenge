package com.challenge.services;

import com.challenge.dto.UserDTO;
import com.challenge.entities.User;
import com.challenge.out.Response;

import java.util.List;

public interface UserService {
    Response save(User body);
    Response update(UserDTO body, int userId);
    Response delete(int id);
    Response findById(int userId);
    Response getAll();

}
