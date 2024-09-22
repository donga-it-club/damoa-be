package com.donga.damoa.study.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.donga.damoa.domain.member.domain.Member;
import com.donga.damoa.domain.study.application.StudyCommandService;
import com.donga.damoa.domain.study.dao.StudyRepository;
import com.donga.damoa.domain.study.domain.RecruitmentStatus;
import com.donga.damoa.domain.study.domain.Study;
import com.donga.damoa.domain.study.domain.StudyFactory;
import com.donga.damoa.domain.study.dto.CreateStudy;
import com.donga.damoa.member.service.TestCreateMemberFactory;
import com.donga.damoa.study.service.util.TestCreateStudyFactory;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreateStudyServiceMockTest {

    @InjectMocks
    StudyCommandService studyCommandService;

    @Mock
    StudyRepository studyRepository;
    @Mock
    StudyFactory studyFactory;

    Member member;

    @BeforeEach
    void setUp() {
        member = TestCreateMemberFactory.createMember("test", "test");
    }

    @Test
    @DisplayName("스터디 모집 글을 정상적으로 등록할 수 있다.")
    void createStudySuccessTest() {
        // given
        String title = "제목";
        String content = "내용";
        LocalDateTime deadline = LocalDateTime.now().plusDays(7);
        RecruitmentStatus recruitmentStatus = RecruitmentStatus.RECRUITING;

        Study study = TestCreateStudyFactory.createStudy(title, content, recruitmentStatus, member);
        when(studyFactory.createStudy(any(CreateStudy.class), any(Member.class))).thenReturn(study);

        // when
        CreateStudy request = new CreateStudy(title, content, null, deadline);
        studyCommandService.save(request, member);

        // then
        verify(studyRepository, times(1)).save(study);
    }

}
