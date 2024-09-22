package com.donga.damoa.domain.study.domain;

import com.donga.damoa.domain.member.domain.Member;
import com.donga.damoa.domain.study.dto.CreateStudy;
import java.time.LocalDateTime;

public abstract class StudyFactory {

    public Study createStudy(CreateStudy request, Member member) {
        Study study = createStudy(request.getTitle(), request.getContent(), request.getLink(),
            request.getRecruitDeadline(), member);
        study.updateRecruitStatus(RecruitmentStatus.RECRUITING);
        return study;
    }

    protected abstract Study createStudy(String title, String content, String link,
        LocalDateTime deadline, Member leader);

}
