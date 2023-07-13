package com.challenge.services.impl;

import com.challenge.commons.UserRole;
import com.challenge.dto.CourseDTO;
import com.challenge.dto.CourseRequestDTO;
import com.challenge.entities.Course;
import com.challenge.entities.User;
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
    public CourseDTO save(Course course) {
        CourseDTO courseDTO = null;

        try {
            var newCourse = courseRepository.save(course);
            courseDTO = mapper.convertValue(newCourse, CourseDTO.class);

        } catch (Exception e) {
            logger.warning("Error: " + e.getMessage());
        }

        return courseDTO;
    }


    @Override
    @Transactional
    public CourseDTO update(CourseRequestDTO body, int courseId) {
        CourseDTO responseCourse = null;

        try {
            Course course = courseRepository.findById(courseId).orElseThrow();

            // El cero lo consieramos como nullidad del campo durante el request.
            if (body.getProfessor() != 0) {
                setProfessorOnUpdate(body, course);

            } else if (body.getStudents() != null) {
                setStudentsOnUpdate(body, course);

            } else {
                course.setName(body.getName() != null ? body.getName() : course.getName());
                course.setSchedule(body.getSchedule() != null ? body.getSchedule() : course.getSchedule());
            }

            course = courseRepository.save(course);
            responseCourse = mapper.convertValue(course, CourseDTO.class);

        } catch (Exception e) {
            logger.warning("Error: " + e.getMessage());
        }

        return responseCourse;
    }

    @Override
    public CourseDTO removeProfessor(int courseId) {
        CourseDTO courseDTO = null;

        try {
            Course course = courseRepository.findById(courseId).orElseThrow();

            if (course.getProfessor() != null) {
                course.setProfessor(null);

                course = courseRepository.save(course);
            }

            courseDTO = mapper.convertValue(course, CourseDTO.class);
        } catch (Exception e) {
            logger.warning("Error: " + e.getMessage());
        }
        return courseDTO;
    }

    @Override
    @Transactional
    public CourseDTO delete(int id) {

        try {
            courseRepository.deleteById(id);
        } catch (Exception e) {
            logger.warning("Error: " + e.getMessage());
        }

        return null;
    }

    @Override
    @Transactional
    public CourseDTO findById(int id) {
        CourseDTO courseDTO = null;

        try {
            var course = courseRepository.findById(id).orElseThrow();
            courseDTO = mapper.convertValue(course, CourseDTO.class);

        } catch (Exception e) {
            logger.warning("Error: " + e.getMessage());
        }

        return courseDTO;

    }

    @Override
    @Transactional
    public List<CourseDTO> getAll() {
        List<CourseDTO> courseDTOS = null;

        try {
            var courses = courseRepository.findAll();
            courseDTOS = mapper.convertValue(courses, new TypeReference<List<CourseDTO>>() {
            });

        } catch (Exception e) {
            logger.warning("Error: " + e.getMessage());
        }
        return courseDTOS;
    }


    // Aux
    private void setProfessorOnUpdate(CourseRequestDTO body, Course course){
        var professor = userRepository.findById(body.getProfessor()).orElseThrow();

        if (professor.getRole() == UserRole.PROFESSOR) {
            course.setProfessor(professor);
        } else {
            // Informar al controller
        }
    }

    private void setStudentsOnUpdate(CourseRequestDTO body, Course course){
        List<User> students = userRepository.findAllById(body.getStudents());

        for (User user : students) {
            if (user.getRole() == UserRole.STUDENT) {
                course.getStudents().add(user);

            } else {
                // Informar al controller
            }
        }
    }
}
