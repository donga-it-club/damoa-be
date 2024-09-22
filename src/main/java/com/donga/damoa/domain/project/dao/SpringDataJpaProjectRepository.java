package com.donga.damoa.domain.project.dao;

import com.donga.damoa.domain.project.domain.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SpringDataJpaProjectRepository implements ProjectRepository {

    private final ProjectJpaRepository repository;

    @Override
    public void save(Project project) {
        repository.save(project);
    }

}
