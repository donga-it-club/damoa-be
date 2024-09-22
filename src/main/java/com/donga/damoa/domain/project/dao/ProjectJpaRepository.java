package com.donga.damoa.domain.project.dao;

import com.donga.damoa.domain.project.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectJpaRepository extends JpaRepository<Project, Long> {

}
