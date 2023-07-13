package com.challenge.services.impl;

import com.challenge.dto.UserDTO;
import com.challenge.entities.User;
import com.challenge.exceptions.ResourceNotFoundException;
import com.challenge.out.Response;
import com.challenge.out.ResponseCode;
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
    public Response save(User body) {
        Response response;
        try {
            User user = userRepository.save(body);
            UserDTO dto = mapper.convertValue(user, UserDTO.class);

            response = new Response(dto, ResponseCode.SUCCESS);
        } catch (Exception e) {
            logger.warning("Error: " + e.getMessage());

            response = new Response(ResponseCode.FAILURE, e.getMessage());
        }

        return response;
    }

    @Override
    @Transactional
    public Response update(UserDTO body, int userId) {
        Response response;

        try {
            User user = userRepository.findById(userId).orElseThrow(() -> {
                throw new ResourceNotFoundException("user", "id", String.valueOf(userId));
            });

            mapper.updateValue(user, body);

            user = userRepository.save(user);

            UserDTO dto = mapper.convertValue(user, UserDTO.class);
            response = new Response(dto, ResponseCode.SUCCESS);
        } catch (Exception e) {
            logger.warning("Error: " + e.getMessage());

            response = new Response(ResponseCode.FAILURE, e.getMessage());
        }

        return response;
    }

    @Override
    @Transactional
    public Response delete(int userId) {
        Response response;

        try {
            User user = userRepository.findById(userId).orElseThrow(() -> {
                throw new ResourceNotFoundException("user", "id", String.valueOf(userId));
            });

            userRepository.deleteById(userId);

            response = new Response(mapper.convertValue(user, UserDTO.class), ResponseCode.SUCCESS);
        } catch (Exception e) {
            logger.warning("Error: " + e.getMessage());

            response = new Response(ResponseCode.FAILURE, e.getMessage());
        }

        return response;
    }

    @Override
    @Transactional
    public Response findById(int userId) {
        Response response;

        try {
            User user = userRepository.findById(userId).orElseThrow(() -> {
                throw new ResourceNotFoundException("user", "id", String.valueOf(userId));
            });
            UserDTO dto = mapper.convertValue(user, UserDTO.class);

            response = new Response(dto, ResponseCode.SUCCESS);
        } catch (Exception e) {
            logger.warning("Error: " + e.getMessage());

            response = new Response(ResponseCode.FAILURE, e.getMessage());
        }

        return response;
    }

    @Override
    @Transactional
    public Response getAll() {
        Response response;

        try {
            List<User> users = userRepository.findAll();

            if (users.isEmpty()) {
                throw new ResourceNotFoundException("User list is empty");
            }
                List<UserDTO> userDtoList = mapper.convertValue(users, new TypeReference<List<UserDTO>>() {
                });

            response = new Response(userDtoList, ResponseCode.SUCCESS);
        } catch (Exception e) {
            logger.warning("Error: " + e.getMessage());

            response = new Response(ResponseCode.FAILURE, e.getMessage());
        }

        return response;
    }
}
