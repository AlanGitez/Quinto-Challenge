package com.challenge.dto;

import com.challenge.commons.CourseSchedule;
import com.challenge.entities.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDTO {

    private String name;

    private CourseSchedule schedule;

    private User professor;

    private List<User> students;
}
