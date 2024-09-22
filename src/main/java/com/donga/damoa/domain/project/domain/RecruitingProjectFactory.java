package com.donga.damoa.domain.project.domain;

import com.donga.damoa.domain.member.domain.Member;
import org.springframework.stereotype.Component;

// 모집 상태를 "RECRUITING"으로 설정하는 생성 클래스
@Component
public class RecruitingProjectFactory extends ProjectFactory {

    @Override
    protected Project createProject(String title, String content, String link, Member leader) {
        Project project = Project.builder()
            .title(title)
            .content(content)
            .link(link)
            .leader(leader)
            .build();

        MemberProject userProject = MemberProject.builder()
            .project(project)
            .member(leader)
            .build();
        project.getMemberProjects().add(userProject);

        return project;
    }

}
