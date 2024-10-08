package com.donga.damoa.domain.member.api;

import com.donga.damoa.domain.member.application.LoginService;
import com.donga.damoa.domain.member.application.MemberQueryService;
import com.donga.damoa.domain.member.application.MemberSignUpService;
import com.donga.damoa.domain.member.domain.Member;
import com.donga.damoa.domain.member.dto.LoginRequest;
import com.donga.damoa.domain.member.dto.LoginResponse;
import com.donga.damoa.domain.member.dto.MyInfoResponse;
import com.donga.damoa.domain.member.dto.SignUpRequest;
import com.donga.damoa.global.common.response.Response;
import com.donga.damoa.global.config.security.CurrentUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Tag(name = "Member(회원)")
public class MemberController {

    private final MemberSignUpService signUpService;
    private final LoginService loginService;
    private final MemberQueryService memberQueryService;

    @Operation(summary = "회원가입 API", description = "사용자는 회원가입을 할 수 있다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "회원가입 성공")
    })
    @PostMapping("/sign-up")
    public Response<Void> signUp(@Valid @RequestBody SignUpRequest request) {
        signUpService.signUp(request);
        return Response.of(HttpStatus.CREATED);
    }

    @Operation(summary = "로그인 API", description = "사용자는 로그인을 할 수 있다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "로그인 성공")
    })
    @PostMapping("/sign-in")
    public Response<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return Response.of(loginService.login(request));
    }

    @Operation(summary = "내 정보 조회 API", description = "사용자는 자신의 정보를 조회할 수 있다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "조회 성공")
    })
    @GetMapping("/me")
    public Response<MyInfoResponse> me(@CurrentUser Member member) {
        return Response.of(memberQueryService.getMyInfo(member));
    }

}
