package com.donga.damoa.domain.project.application;

import com.donga.damoa.domain.member.domain.Member;
import com.donga.damoa.domain.project.dao.ProjectRepository;
import com.donga.damoa.domain.project.domain.Project;
import com.donga.damoa.domain.project.domain.ProjectFactory;
import com.donga.damoa.domain.project.dto.CreateProject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectCommandService {

    private final ProjectRepository projectRepository;
    private final ProjectFactory projectFactory;

    @Transactional
    public void registerProject(CreateProject request, Member member) {
        Project project = projectFactory.createProject(request, member);
        projectRepository.save(project);
        log.info("프로젝트 등록 완료, ID : {}", project.getId());
    }

}
