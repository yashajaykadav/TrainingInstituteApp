package com.nexanova.course_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nexanova.course_service.entity.Module;

public interface ModuleRepository extends JpaRepository<Module,Long> {
}
