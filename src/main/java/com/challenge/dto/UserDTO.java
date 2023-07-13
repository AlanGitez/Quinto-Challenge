package com.challenge.dto;

import com.challenge.commons.UserRole;
import com.challenge.entities.Course;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Long dni;

    private String name;

    private String lastname;

    private String email;

    private UserRole role;

    private List<Course> courses = new ArrayList<>();
}
