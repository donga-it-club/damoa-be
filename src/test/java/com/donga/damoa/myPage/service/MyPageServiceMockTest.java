package com.donga.damoa.myPage.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.donga.damoa.domain.member.dao.MemberRepository;
import com.donga.damoa.domain.member.domain.EnrollmentStatus;
import com.donga.damoa.domain.member.domain.Member;
import com.donga.damoa.domain.myPage.application.MyPageDeactivateService;
import com.donga.damoa.domain.myPage.application.MyPageQueryService;
import com.donga.damoa.domain.myPage.application.MyPageService;
import com.donga.damoa.domain.myPage.dao.MyPageRepository;
import com.donga.damoa.domain.myPage.domain.MyPage;
import com.donga.damoa.domain.myPage.dto.MyPageRequest;
import com.donga.damoa.domain.myPage.dto.MyPageResponse;
import com.donga.damoa.domain.myPage.error.MyPageErrorCode;
import com.donga.damoa.domain.myPage.error.exception.MyPageNotFoundException;
import com.donga.damoa.member.service.TestCreateMemberFactory;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class MyPageServiceMockTest {

    @Mock
    MyPageRepository myPageRepository;

    @Mock
    MemberRepository memberRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    MyPageQueryService myPageQueryService;

    @InjectMocks
    MyPageDeactivateService myPageDeactivateService;

    @InjectMocks
    MyPageService myPageService;

    @Test
    @DisplayName("Successfully updates MyPage information.")
    void updateMyPage_success() {
        // given
        Member member = TestCreateMemberFactory.createMember("test@donga.ac.kr", "encoded-password");

        // 새로운 재학 상태와 비밀번호
        MyPageRequest request = new MyPageRequest(EnrollmentStatus.GRADUATED, "new password");

        // Mock 설정 - MyPage가 존재하는 경우 Optional.of() 반환
        when(myPageRepository.findByMemberId(member.getId())).thenReturn(Optional.of(new MyPage(member, EnrollmentStatus.ENROLLED, List.of())));

        // Mock 설정 - 비밀번호 인코딩 로직
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encoded-new-password");

        // when
        myPageService.updateMyPage(member, request);

        // then
        assertThat(member.getEnrollmentStatus()).isEqualTo(EnrollmentStatus.GRADUATED);  // 재학 상태가 업데이트 되었는지 확인
        verify(passwordEncoder).encode(request.getPassword());  // 비밀번호 인코딩이 호출되었는지 확인
        assertThat(member.getPassword()).isEqualTo("encoded-new-password");  // 업데이트된 비밀번호 확인
        verify(memberRepository).save(member);  // Member가 저장되었는지 확인
    }

    @Test
    @DisplayName("Does not update password if it's null.")
    void updateMyPage_withoutPassword() {
        // given
        Member member = TestCreateMemberFactory.createMember("test@donga.ac.kr", "encoded-password");

        // 비밀번호는 null로 설정하고 재학 상태만 변경
        MyPageRequest request = new MyPageRequest(EnrollmentStatus.GRADUATED, null);

        // Mock 설정 - MyPage가 존재하는 경우 Optional.of() 반환
        when(myPageRepository.findByMemberId(member.getId())).thenReturn(Optional.of(new MyPage(member, EnrollmentStatus.ENROLLED, List.of())));

        // when
        myPageService.updateMyPage(member, request);

        // then
        assertThat(member.getEnrollmentStatus()).isEqualTo(EnrollmentStatus.GRADUATED);  // 재학 상태가 업데이트 되었는지 확인
        assertThat(member.getPassword()).isEqualTo("encoded-password");  // 비밀번호는 그대로 유지
        verify(passwordEncoder, never()).encode(anyString());  // 비밀번호 인코딩은 호출되지 않아야 함
        verify(memberRepository).save(member);  // Member가 저장되었는지 확인
    }

    @Test
    @DisplayName("Throws exception if Member is not found.")
    void updateMyPage_fail() {
        // given
        Member member = TestCreateMemberFactory.createMember("test@donga.ac.kr", "encoded password");

        MyPageRequest request = new MyPageRequest(EnrollmentStatus.GRADUATED, "new password");

        // Mock 설정 - findByMemberId가 Optional.empty()를 반환하도록 설정 (Member가 존재하지 않음)
        when(myPageRepository.findByMemberId(member.getId())).thenReturn(Optional.empty());

        // when
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class, () -> myPageService.updateMyPage(member, request)
        );

        // then
        assertThat(exception.getMessage()).isEqualTo("MyPage not found for this member.");
        verify(memberRepository, never()).save(any(Member.class));  // Member 저장이 호출되지 않아야 함
    }

    @Test
    @DisplayName("Successfully returns MyPageResponse when MyPage is found.")
    void getMyPage_success() {
        // given
        Member member = TestCreateMemberFactory.createMember("test@donga.ac.kr", "encoded password");

        MyPage myPage = new MyPage(member, EnrollmentStatus.ENROLLED, List.of());

        // Mock 설정 - myPageRepository에서 MyPage를 반환하도록 설정
        when(myPageRepository.findByMemberId(member.getId())).thenReturn(Optional.of(myPage));

        // when
        MyPageResponse response = myPageQueryService.getMyPage(member);

        // then
        assertThat(response.getName()).isEqualTo(member.getName());
        assertThat(response.getEmail()).isEqualTo(member.getEmail());
    }

    @Test
    @DisplayName("Throws MyPageNotFoundException when MyPage is not found.")
    void getMyPage_fail() {
        // given
        Member member = TestCreateMemberFactory.createMember("test@example.com", "encoded password");

        // Mock 설정 - myPageRepository에서 MyPage가 없을 경우 Optional.empty()를 반환하도록 설정
        when(myPageRepository.findByMemberId(member.getId())).thenReturn(Optional.empty());

        // when
        MyPageNotFoundException exception = assertThrows(
            MyPageNotFoundException.class, () -> myPageQueryService.getMyPage(member)
        );

        // then
        assertThat(exception.getErrorCode()).isEqualTo(MyPageErrorCode.MY_PAGE_NOT_FOUND);
    }

    @Test
    @DisplayName("Successfully deactivates account (soft delete).")
    void deactivateAccount_success() {
        // given
        Member member = TestCreateMemberFactory.createMember("test@example.com", "encoded-password");

        // Mock 설정 - MyPage가 존재하는 경우
        when(myPageRepository.findByMemberId(member.getId())).thenReturn(Optional.of(new MyPage(member, EnrollmentStatus.ENROLLED, List.of())));

        // when
        myPageDeactivateService.deactivateAccount(member);

        // then
        assertThat(member.isDeleted()).isTrue();  // 삭제 상태 확인
        verify(memberRepository).save(member);  // Member 저장이 호출되었는지 확인
    }

}
