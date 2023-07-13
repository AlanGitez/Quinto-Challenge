package com.challenge.services;


import com.challenge.dto.CourseDTO;
import com.challenge.dto.CourseRequestDTO;
import com.challenge.entities.Course;

import java.util.List;

public interface CourseService {
    CourseDTO save(Course course);
    CourseDTO update(CourseRequestDTO request, int courseId);
    CourseDTO removeProfessor(int courseId);
    CourseDTO delete(int id);
    CourseDTO findById(int id);
    List<CourseDTO> getAll();

}
