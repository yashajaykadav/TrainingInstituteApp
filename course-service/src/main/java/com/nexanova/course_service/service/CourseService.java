package com.nexanova.course_service.service;


import com.nexanova.course_service.entity.Course;
import com.nexanova.course_service.repository.CourseRepository;
import com.nexanova.course_service.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nexanova.course_service.entity.Module;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    public Course saveCourse(Course course){
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public List<Module> getModuleByCourseId(Long courseId){
        return moduleRepository.findByCourseId(courseId);
    }

    public Module addModuleToCourse(Long courseId,Module module){
        Optional<Course> courseOptional = courseRepository.findById(courseId);

        if(courseOptional.isPresent()) {
            Course course = courseOptional.get();
            module.setCourse(course);
            return moduleRepository.save(module);
        }else{
            throw new RuntimeException("Course Not Found With Id "+courseId);
        }
    }
}
