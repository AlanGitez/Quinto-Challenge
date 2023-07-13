package com.challenge.services;


import com.challenge.dto.CourseDTO;
import com.challenge.dto.CourseRequestDTO;
import com.challenge.entities.Course;
import com.challenge.out.Response;

import java.util.List;

public interface CourseService {
    Response save(Course course);
    Response update(CourseRequestDTO request, int courseId);
    Response removeProfessor(int courseId);
    Response delete(int id);
    Response findById(int id);
    Response getAll();

}
