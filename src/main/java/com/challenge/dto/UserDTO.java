package com.challenge.dto;

import com.challenge.entities.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long dni;

    private String name;

    private String lastname;

    private String email;

    private List<Course> courses = new ArrayList<>();
}
