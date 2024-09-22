package com.donga.damoa.domain.project.api;

import com.donga.damoa.domain.member.domain.Member;
import com.donga.damoa.domain.project.application.ProjectCommandService;
import com.donga.damoa.domain.project.dto.CreateProject;
import com.donga.damoa.global.common.response.Response;
import com.donga.damoa.global.config.security.CurrentUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Project(프로젝트) API")
@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectCommandService commandService;

    // 새로운 프로젝트 등록 API
    @Operation(summary = "프로젝트 생성 API", description = "사용자는 프로젝트를 생성할 수 있다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "프로젝트 생성 성공")
    })
    @PostMapping("/register")
    public Response<Void> registerProject(@CurrentUser Member member, @Valid @RequestBody
    CreateProject request) {
        commandService.registerProject(request, member);
        return Response.of(HttpStatus.CREATED);
    }

}
