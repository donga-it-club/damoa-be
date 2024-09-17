package com.donga.damoa.myPage.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.donga.damoa.domain.member.dao.MemberRepository;
import com.donga.damoa.domain.member.domain.EnrollmentStatus;
import com.donga.damoa.domain.member.domain.Member;
import com.donga.damoa.domain.myPage.application.MyPageQueryService;
import com.donga.damoa.domain.myPage.dao.MyPageRepository;
import com.donga.damoa.domain.myPage.domain.AccountStatus;
import com.donga.damoa.domain.myPage.domain.MyPage;
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

@ExtendWith(MockitoExtension.class)
public class MyPageServiceMockTest {

    @Mock
    MyPageRepository myPageRepository;

    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    MyPageQueryService myPageQueryService;

    @Test
    @DisplayName("Successfully returns MyPageResponse when MyPage is found.")
    void getMyPage_success() {
        // given
        Member member = TestCreateMemberFactory.createMember("test@donga.ac.kr", "encoded-password");

        MyPage myPage = new MyPage(member, EnrollmentStatus.ENROLLED, List.of());

        // Mock 설정 - memberRepository.existsById가 true를 반환하도록 설정
        when(memberRepository.existsById(member.getId())).thenReturn(true);

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
        Member member = TestCreateMemberFactory.createMember("test@example.com", "encoded-password");

        // Mock 설정 - memberRepository.existsById가 true를 반환하도록 설정
        when(memberRepository.existsById(member.getId())).thenReturn(true);

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

        // Mock 설정 - memberRepository.existsById가 true를 반환하도록 설정
        when(memberRepository.existsById(member.getId())).thenReturn(true);

        // when
        myPageQueryService.deactivateAccount(member);

        // then
        verify(memberRepository).save(member);
        assertThat(member.getAccountStatus()).isEqualTo(AccountStatus.DEACTIVATED);
    }
}
