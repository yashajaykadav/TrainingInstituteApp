package com.nexanova.course_service.controller;


import com.nexanova.course_service.entity.Course;
import com.nexanova.course_service.service.CourseService;
import com.nexanova.course_service.entity.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/courses")
public class CourseController {
    @Autowired
    private CourseService  courseService;

    @PostMapping
    public Course createCourse(@RequestBody Course course){
        return courseService.saveCourse(course);
    }

    @GetMapping
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @PostMapping("/{courseId}/modules")
    public Module addmodule(@PathVariable Long courseId,@RequestBody Module module){
        return courseService.addModuleToCourse(courseId,module);
    }

}
