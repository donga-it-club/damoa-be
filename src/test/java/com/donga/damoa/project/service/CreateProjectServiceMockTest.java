package com.donga.damoa.project.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.donga.damoa.domain.member.domain.Member;
import com.donga.damoa.domain.project.application.ProjectCommandService;
import com.donga.damoa.domain.project.dao.ProjectRepository;
import com.donga.damoa.domain.project.domain.Project;
import com.donga.damoa.domain.project.domain.ProjectFactory;
import com.donga.damoa.domain.project.dto.CreateProject;
import com.donga.damoa.member.service.TestCreateMemberFactory;
import com.donga.damoa.project.service.util.TestCreateProjectFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreateProjectServiceMockTest {

    @InjectMocks
    ProjectCommandService projectCommandService;

    @Mock
    ProjectFactory projectFactory;
    @Mock
    ProjectRepository projectRepository;

    Member member;

    @BeforeEach
    void setUp() {
        member = TestCreateMemberFactory.createMember("test", "test");
    }

    @Test
    @DisplayName("프로젝트 생성이 정상적으로 수행된다면 프로젝트를 저장한다.")
    void createProjectSuccessTest() {
        // given
        Project project = TestCreateProjectFactory.createProject("제목", "내용", "www.test.com",
            member);
        when(projectFactory.createProject(any(CreateProject.class), any(Member.class)))
            .thenReturn(project);

        // when
        CreateProject request = new CreateProject("제목", "내용", "www.test.com");
        projectCommandService.registerProject(request, member);

        // then
        verify(projectRepository, times(1)).save(project);
    }

}
