package com.donga.damoa.domain.study.dao;

import com.donga.damoa.domain.study.domain.Study;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SpringDataJpaStudyRepository implements StudyRepository {

    private final StudyJpaRepository jpaRepository;

    @Override
    public void save(Study study) {
        jpaRepository.save(study);
    }

}
