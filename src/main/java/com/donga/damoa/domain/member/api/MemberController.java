package com.donga.damoa.domain.member.api;

import com.donga.damoa.domain.member.application.SignUpService;
import com.donga.damoa.domain.member.dto.SignUpRequest;
import com.donga.damoa.global.common.response.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final SignUpService signUpService;

    @PostMapping("/sign-up")
    public Response<Void> signUp(@Valid @RequestBody SignUpRequest request) {
        signUpService.signUp(request);
        return Response.of(HttpStatus.CREATED);
    }

}
