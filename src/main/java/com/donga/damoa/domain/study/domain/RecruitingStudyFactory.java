package com.donga.damoa.domain.study.domain;

import com.donga.damoa.domain.member.domain.Member;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class RecruitingStudyFactory extends StudyFactory {

    @Override
    protected Study createStudy(String title, String content, String link, LocalDateTime deadline,
        Member leader) {
        Study study = Study.builder()
            .title(title)
            .content(content)
            .link(link)
            .recruitDeadline(deadline)
            .leader(leader)
            .build();

        MemberStudy memberStudy = MemberStudy.builder()
            .study(study)
            .member(leader)
            .build();

        study.getMemberStudies().add(memberStudy);

        return study;
    }

}
