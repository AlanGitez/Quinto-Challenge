package com.challenge.services.impl;

import com.challenge.dto.UserDTO;
import com.challenge.entities.User;
import com.challenge.repositories.UserRepository;
import com.challenge.services.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserService.class.toString());

    @Autowired
    ObjectMapper mapper;

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDTO save(User body) {
        UserDTO dto = null;

        try {
            User user = userRepository.save(body);

            dto = mapper.convertValue(user, UserDTO.class);

        } catch (Exception e) {
            logger.warning("Error: " + e.getMessage());
        }

        return dto;
    }

    @Override
    @Transactional
    public UserDTO update(UserDTO body, int userId) {
        UserDTO dto = null;

        try {
            User user = userRepository.findById(userId).orElseThrow();

            mapper.updateValue(user, body);

            user = userRepository.save(user);
            dto = mapper.convertValue(user, UserDTO.class);

        } catch (Exception e) {
            logger.warning("Error: " + e.getMessage());
        }

        return dto;
    }

    @Override
    @Transactional
    public UserDTO delete(int userId) {

        try {
            userRepository.deleteById(userId);

        } catch (Exception e) {
            logger.warning("Error: " + e.getMessage());
        }

        return null;
    }

    @Override
    @Transactional
    public UserDTO findById(int userId) {
        UserDTO dto = null;

        try {
            User user = userRepository.findById(userId).orElseThrow();
            dto = mapper.convertValue(user, UserDTO.class);

        } catch (Exception e) {
            logger.warning("Error: " + e.getMessage());
        }

        return dto;
    }

    @Override
    @Transactional
    public List<UserDTO> getAll() {
        List<UserDTO> userDtoList = null;

        try {
            List<User> users = userRepository.findAll();
            userDtoList = mapper.convertValue(users, new TypeReference<List<UserDTO>>() {
            });

        } catch (Exception e) {
            logger.warning("Error: " + e.getMessage());
        }

        return userDtoList;
    }
}
