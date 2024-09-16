package com.donga.damoa.domain.member.application;

import com.donga.damoa.domain.member.dto.LoginRequest;
import com.donga.damoa.domain.member.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthLoginService loginService;

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        String accessToken = loginService.login(request.getEmail(), request.getPassword());
        return new LoginResponse(accessToken);
    }

}
