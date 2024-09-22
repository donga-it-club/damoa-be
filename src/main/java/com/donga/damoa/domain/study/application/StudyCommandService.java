package com.donga.damoa.domain.study.application;

import com.donga.damoa.domain.member.domain.Member;
import com.donga.damoa.domain.study.dao.StudyRepository;
import com.donga.damoa.domain.study.domain.Study;
import com.donga.damoa.domain.study.domain.StudyFactory;
import com.donga.damoa.domain.study.dto.CreateStudy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudyCommandService {

    private final StudyRepository studyRepository;
    private final StudyFactory studyFactory;

    @Transactional
    public void save(CreateStudy request, Member member) {
        Study study = studyFactory.createStudy(request, member);
        studyRepository.save(study);
        log.info("스터디 등록 완료, ID : {}", study.getId());
    }

}
