package com.challenge.services.impl;

import com.challenge.commons.UserRole;
import com.challenge.dto.CourseDTO;
import com.challenge.dto.CourseRequestDTO;
import com.challenge.entities.Course;
import com.challenge.entities.User;
import com.challenge.exceptions.ResourceNotFoundException;
import com.challenge.out.Response;
import com.challenge.out.ResponseCode;
import com.challenge.repositories.CourseRepository;
import com.challenge.repositories.UserRepository;
import com.challenge.services.CourseService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class CourseServiceImpl implements CourseService {
    private static final Logger logger = Logger.getLogger(CourseServiceImpl.class.toString());

    @Autowired
    ObjectMapper mapper;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public Response save(Course course) {
        Response response;
        try {
            var newCourse = courseRepository.save(course);
            CourseDTO courseDTO = mapper.convertValue(newCourse, CourseDTO.class);

            response = new Response(courseDTO, ResponseCode.SUCCESS);

        } catch (Exception e) {
            logger.warning("Error: " + e.getMessage());

            response = new Response(ResponseCode.FAILURE, e.getMessage());
        }

        return response;
    }


    @Override
    @Transactional
    public Response update(CourseRequestDTO body, int courseId) {
        Response response = new Response();

        try {
            Course course = courseRepository.findById(courseId).orElseThrow(() -> {
                throw new ResourceNotFoundException("course", "id", String.valueOf(courseId));
            });

            // El cero lo consieramos como nullidad del campo durante el request.
            if (body.getProfessor() != 0) {
                setProfessorOnUpdate(body, course, response);

            }
            if (body.getStudents() != null) {
                setStudentsOnUpdate(body, course, response);

            }
            if (body.getStudents() == null && body.getProfessor() == 0) {
                course.setName(body.getName() != null ? body.getName() : course.getName());
                course.setSchedule(body.getSchedule() != null ? body.getSchedule() : course.getSchedule());
            }

            course = courseRepository.save(course);
            CourseDTO responseCourse = mapper.convertValue(course, CourseDTO.class);

            if (response.getStatusCode() == null) {
                response.setEntity(responseCourse);
                response.setStatusCode(ResponseCode.SUCCESS.getStatusCode());
                response.setMsg(ResponseCode.SUCCESS.getText());
            }
        } catch (Exception e) {
            logger.warning("Error: " + e.getMessage());

            response = new Response(ResponseCode.FAILURE, e.getMessage());
        }

        return response;
    }

    @Override
    @Transactional
    public Response removeProfessor(int courseId) {
        Response response;

        try {
            Course course = courseRepository.findById(courseId).orElseThrow(() -> {
                throw new ResourceNotFoundException("course", "id", String.valueOf(courseId));
            });

            if (course.getProfessor() != null) {
                course.setProfessor(null);

                course = courseRepository.save(course);
            }

            CourseDTO courseDTO = mapper.convertValue(course, CourseDTO.class);

            response = new Response(courseDTO, ResponseCode.SUCCESS);
        } catch (Exception e) {
            logger.warning("Error: " + e.getMessage());

            response = new Response(ResponseCode.FAILURE, e.getMessage());
        }
        return response;
    }

    @Override
    @Transactional
    public Response delete(int id) {
        Response response;

        try {
            courseRepository.deleteById(id);

            response = new Response(null, ResponseCode.SUCCESS);
        } catch (Exception e) {
            logger.warning("Error: " + e.getMessage());

            response = new Response(ResponseCode.FAILURE, e.getMessage());
        }

        return response;
    }

    @Override
    @Transactional
    public Response findById(int courseId) {
        Response response;

        try {
            var course = courseRepository.findById(courseId).orElseThrow(() -> {
                throw new ResourceNotFoundException("course", "id", String.valueOf(courseId));
            });
            CourseDTO courseDTO = mapper.convertValue(course, CourseDTO.class);

            response = new Response(courseDTO, ResponseCode.SUCCESS);

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
            List<Course> courses = courseRepository.findAll();

            if (courses.isEmpty()) {
                throw new ResourceNotFoundException("Courses list is empty");
            }
            List<CourseDTO> courseDTOS = mapper.convertValue(courses, new TypeReference<List<CourseDTO>>() {
            });

            response = new Response(courseDTOS, ResponseCode.SUCCESS);
        } catch (Exception e) {
            logger.warning("Error: " + e.getMessage());

            response = new Response(ResponseCode.FAILURE, e.getMessage());
        }
        return response;
    }


    // Aux
    private void setProfessorOnUpdate(CourseRequestDTO body, Course course, Response response){
        var user = userRepository.findById(body.getProfessor()).orElseThrow(() -> {
            throw new ResourceNotFoundException("user", "id", String.valueOf(body.getProfessor()));
        });

        if (user.getRole() == UserRole.PROFESSOR) {
            course.setProfessor(user);
        } else {
            response.setStatusCode(ResponseCode.FAILURE.getStatusCode());
            response.setMsg(ResponseCode.FAILURE.getText());
            response.setDetailedError("El Usuario no es profesor, es: " + user.getRole());
        }
    }

    private void setStudentsOnUpdate(CourseRequestDTO body, Course course, Response response){
        List<User> users = userRepository.findAllById(body.getStudents());

        if (users.isEmpty()) {
            throw new ResourceNotFoundException("the list of users with the role of students is empty with the ids received");
        }

        for (User user : users) {
            if (user.getRole() == UserRole.STUDENT) {
                course.getStudents().add(user);
            } else {
                response.setStatusCode(ResponseCode.FAILURE.getStatusCode());
                response.setMsg(ResponseCode.FAILURE.getText());
                response.setDetailedError("Al menos uno de los usuarios no es estudiante, es: " + user.getRole());
            }
        }
    }
}
