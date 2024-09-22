package com.donga.damoa.member.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.donga.damoa.domain.member.application.MemberSignUpService;
import com.donga.damoa.domain.member.dao.MemberRepository;
import com.donga.damoa.domain.member.domain.Member;
import com.donga.damoa.domain.member.domain.MemberCreator;
import com.donga.damoa.domain.member.dto.SignUpRequest;
import com.donga.damoa.domain.member.error.MemberErrorCode;
import com.donga.damoa.domain.member.error.exception.EmailDuplicateException;
import com.donga.damoa.domain.myPage.dao.MyPageRepository;
import com.donga.damoa.domain.myPage.domain.MyPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class SignUpServiceMockTest {

    @Mock
    MemberRepository memberRepository;
    @Mock
    MemberCreator memberCreator;
    @Mock
    MyPageRepository myPageRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    @InjectMocks
    MemberSignUpService signUpService;

    @Test
    @DisplayName("중복된 이메일로 회원가입 시에 예외가 발생한다.")
    void signUpWithDuplicateEmailThrowsException() {
        // given
        String duplicateEmail = "test@example.com";
        SignUpRequest request = TestSignUpRequestFactory.createSignUpWithEmail(duplicateEmail);

        when(memberRepository.existsByEmail(duplicateEmail)).thenReturn(true);

        // when & then
        // 중복된 이메일로 가입을 시도할 때 EmailDuplicateException이 발생하는지 검증
        EmailDuplicateException exception = assertThrows(
            EmailDuplicateException.class, () -> signUpService.signUp(request)
        );

        assertEquals(MemberErrorCode.DUPLICATE_EMAIL, exception.getErrorCode());
        verify(memberCreator, never()).createMember(any(SignUpRequest.class));
    }

    @Test
    @DisplayName("회원 가입 시 MyPage가 생성된다.")
    void signUp_createsMyPage() {
        // given
        SignUpRequest request = new SignUpRequest("test@example.com", "password", "Test User", "major", "minor", "ENROLLED", "prLink");

        when(memberRepository.existsByEmail(request.getEmail())).thenReturn(false);
        Member member = TestCreateMemberFactory.createMember(request.getEmail(), passwordEncoder.encode(request.getPassword()));
        when(memberCreator.createMember(request)).thenReturn(member);

        // when
        signUpService.signUp(request);

        // then
        verify(memberRepository).save(member);
        verify(myPageRepository).save(any(MyPage.class));  // MyPage 생성 확인
    }

    @Test
    @DisplayName("MyPage 생성에 실패할 경우 예외가 발생한다.")
    void signUp_failsOnMyPageCreation() {
        // given
        SignUpRequest request = new SignUpRequest("test@example.com", "password", "Test User", "major", "minor", "ENROLLED", "prLink");

        when(memberRepository.existsByEmail(request.getEmail())).thenReturn(false);
        Member member = TestCreateMemberFactory.createMember(request.getEmail(), passwordEncoder.encode(request.getPassword()));
        when(memberCreator.createMember(request)).thenReturn(member);

        // MyPage 생성 실패를 모의
        doThrow(new RuntimeException("MyPage creation failed")).when(myPageRepository).save(any(MyPage.class));

        // when & then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> signUpService.signUp(request));
        assertEquals("MyPage creation failed", exception.getMessage());
    }

}
