package com.donga.damoa.domain.study.dao;

import com.donga.damoa.domain.study.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyJpaRepository extends JpaRepository<Study, Long> {

}
