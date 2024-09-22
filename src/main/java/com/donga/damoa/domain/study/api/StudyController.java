package com.donga.damoa.domain.study.api;

import com.donga.damoa.domain.member.domain.Member;
import com.donga.damoa.domain.study.application.StudyCommandService;
import com.donga.damoa.domain.study.dto.CreateStudy;
import com.donga.damoa.global.common.response.Response;
import com.donga.damoa.global.config.security.CurrentUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/studies")
public class StudyController {

    private final StudyCommandService studyCommandService;

    @PostMapping("/register")
    public Response<Void> createStudy(@CurrentUser Member member,
        @Valid @RequestBody CreateStudy request) {
        studyCommandService.save(request, member);
        return Response.of(HttpStatus.CREATED);
    }

}
