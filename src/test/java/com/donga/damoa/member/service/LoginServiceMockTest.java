package com.donga.damoa.member.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.donga.damoa.domain.member.application.AuthLoginService;
import com.donga.damoa.domain.member.application.IdPasswordLoginService;
import com.donga.damoa.domain.member.application.LoginService;
import com.donga.damoa.domain.member.dao.MemberRepository;
import com.donga.damoa.domain.member.domain.Member;
import com.donga.damoa.domain.member.dto.LoginRequest;
import com.donga.damoa.domain.member.error.MemberErrorCode;
import com.donga.damoa.domain.member.error.exception.EmailNotFoundException;
import com.donga.damoa.domain.member.error.exception.InvalidPasswordException;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class LoginServiceMockTest {

    @InjectMocks
    LoginService loginService;
    @InjectMocks
    IdPasswordLoginService idPasswordLoginService;

    @Mock
    AuthLoginService authLoginService;
    @Mock
    MemberRepository memberRepository;
    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("로그인이 정상적으로 수행된다면 JWT 토큰을 발급해야 한다.")
    void return_jwtToken_when_validLogin() {
        // given
        String email = "test@test.com";
        String rawPassword = "test";

        when(authLoginService.login(email, rawPassword)).thenReturn("jwt-token");

        // when
        LoginRequest request = new LoginRequest(email, rawPassword);
        String jwtToken = loginService.login(request);

        // then
        assertThat(jwtToken).isEqualTo("jwt-token");
    }

    @Test
    @DisplayName("이메일이 DB에 존재하지 않으면 EmailNotFoundException 예외가 발생한다.")
    void throwException_when_NotExistEmail() {
        // given
        String email = "test@test.com";
        String password = "test";

        when(memberRepository.findMemberByEmail(email)).thenThrow(
            new EmailNotFoundException(MemberErrorCode.EMAIL_NOT_FOUND));

        // when
        EmailNotFoundException exception = assertThrows(
            EmailNotFoundException.class,
            () -> idPasswordLoginService.login(email, password));

        // then
        assertThat(exception.getErrorCode()).isEqualTo(MemberErrorCode.EMAIL_NOT_FOUND);
    }

    @Test
    @DisplayName("사용자의 비밀번호가 일치하지 않는 경우 InvalidPasswordException 예외가 발생한다.")
    void throwException_when_invalid_password() {
        // given
        String email = "test@test.com";
        String rawPassword = "test";
        String encodedPassword = "encoded-password";

        Member member = TestCreateMemberFactory.createMember(email, encodedPassword);

        when(memberRepository.findMemberByEmail(email)).thenReturn(Optional.of(member));
        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(false);

        // when
        InvalidPasswordException exception = assertThrows(
            InvalidPasswordException.class,
            () -> idPasswordLoginService.login(email, rawPassword));

        // then
        assertThat(exception.getErrorCode()).isEqualTo(MemberErrorCode.INVALID_PASSWORD);
    }

}
