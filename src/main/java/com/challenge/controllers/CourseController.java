package com.challenge.controllers;

import com.challenge.dto.CourseDTO;
import com.challenge.dto.CourseRequestDTO;
import com.challenge.entities.Course;
import com.challenge.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    CourseService service;

    @PostMapping("/courses/add")
    public CourseDTO save(@RequestBody Course body) {
        return service.save(body);
    }

    @PutMapping("/courses/{courseId}")
    public CourseDTO update(
            @RequestBody CourseRequestDTO body,
            @PathVariable int courseId) {

        return service.update(body, courseId);
    }

    @PutMapping("/courses/remove-professor/{courseId}")
    public CourseDTO removeProfessor(
            @PathVariable int courseId) {

        return service.removeProfessor(courseId);
    }

    @DeleteMapping("/courses/{courseId}")
    public CourseDTO delete(@PathVariable int courseId) {

        return service.delete(courseId);
    }

    @GetMapping("/courses/{courseId}")
    public CourseDTO findByid(@PathVariable int courseId) {

        return service.findById(courseId);
    }

    @GetMapping("/courses/all")
    public List<CourseDTO> getAll() {

        return service.getAll();
    }

}
