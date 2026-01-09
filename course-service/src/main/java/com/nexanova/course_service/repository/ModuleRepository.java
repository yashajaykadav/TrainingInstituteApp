package com.nexanova.course_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nexanova.course_service.entity.Module;

import java.util.List;

public interface ModuleRepository extends JpaRepository<Module,Long> {

    List<Module> findByCourseId(Long courseId);}
