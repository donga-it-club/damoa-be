package com.donga.damoa.domain.member.application;

import com.donga.damoa.domain.member.domain.Member;
import com.donga.damoa.domain.member.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AbstractLoginService loginService;

    @Transactional(readOnly = true)
    public String login(LoginRequest request) {
        Member member = loginService.login(request.getEmail(), request.getPassword());
        if (member != null) {
            return "loginSuccess";
        }
        return "loginFailed";
    }

}
