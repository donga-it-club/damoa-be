package com.donga.damoa.member.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.donga.damoa.domain.member.application.SignUpService;
import com.donga.damoa.domain.member.dao.MemberRepository;
import com.donga.damoa.domain.member.domain.MemberCreator;
import com.donga.damoa.domain.member.dto.SignUpRequest;
import com.donga.damoa.domain.member.error.MemberErrorCode;
import com.donga.damoa.domain.member.error.exception.EmailDuplicateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SignUpServiceMockTest {

    @Mock
    MemberRepository memberRepository;
    @Mock
    MemberCreator memberCreator;
    @InjectMocks
    SignUpService signUpService;

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
}
