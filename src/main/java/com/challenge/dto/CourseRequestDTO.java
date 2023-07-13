package com.challenge.dto;

import com.challenge.commons.CourseSchedule;
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
public class CourseRequestDTO {

    private String name;

    private CourseSchedule schedule;

    private int professor;

    private List<Integer> students;
}