package com.challenge.controllers;

import com.challenge.dto.CourseDTO;
import com.challenge.dto.CourseRequestDTO;
import com.challenge.entities.Course;
import com.challenge.out.Response;
import com.challenge.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    CourseService service;

    @PostMapping("/courses/add")
    public Response save(@RequestBody Course body) {
        return service.save(body);
    }

    @PutMapping("/courses/{courseId}")
    public Response update(
            @RequestBody CourseRequestDTO body,
            @PathVariable int courseId) {

        return service.update(body, courseId);
    }

    @PutMapping("/courses/remove-professor/{courseId}")
    public Response removeProfessor(
            @PathVariable int courseId) {

        return service.removeProfessor(courseId);
    }

    @DeleteMapping("/courses/{courseId}")
    public Response delete(@PathVariable int courseId) {

        return service.delete(courseId);
    }

    @GetMapping("/courses/{courseId}")
    public Response findByid(@PathVariable int courseId) {

        return service.findById(courseId);
    }

    @GetMapping("/courses/all")
    public Response getAll() {

        return service.getAll();
    }

}
