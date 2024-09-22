package com.donga.damoa.domain.project.domain;

import com.donga.damoa.domain.member.domain.Member;
import com.donga.damoa.domain.project.dto.CreateProject;

public abstract class ProjectFactory {

    public Project createProject(CreateProject request, Member leader) {
        Project project = createProject(request.getTitle(), request.getContent(), request.getLink(),
            leader);
        project.updateRecruitmentStatus(RecruitmentStatus.RECRUITING);
        return project;
    }

    protected abstract Project createProject(String title, String content, String link,
        Member leader);

}
