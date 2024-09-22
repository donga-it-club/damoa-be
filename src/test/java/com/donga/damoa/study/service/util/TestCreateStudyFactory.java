package com.donga.damoa.study.service.util;

import com.donga.damoa.domain.member.domain.Member;
import com.donga.damoa.domain.study.domain.RecruitmentStatus;
import com.donga.damoa.domain.study.domain.Study;
import org.springframework.test.util.ReflectionTestUtils;

public class TestCreateStudyFactory {

    public static Study createStudy(String title, String content,
        RecruitmentStatus recruitmentStatus, Member leader) {
        Study study = Study.builder()
            .title(title)
            .content(content)
            .leader(leader)
            .build();
        ReflectionTestUtils.setField(study, "status", recruitmentStatus);
        return study;
    }

}
