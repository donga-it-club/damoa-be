package com.donga.damoa.domain.myPage.api;

import com.donga.damoa.domain.member.domain.Member;
import com.donga.damoa.domain.myPage.application.MyPageService;
import com.donga.damoa.domain.myPage.dto.MyPageRequest;
import com.donga.damoa.domain.myPage.dto.MyPageResponse;
import com.donga.damoa.global.common.response.Response;
import com.donga.damoa.global.config.security.CurrentUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myPage")
@RequiredArgsConstructor
@Tag(name = "MyPage")
public class MyPageController {

    private final MyPageService myPageService;

    @Operation(summary = "Retrieve MyPage information", description = "Members can retrieve their MyPage information.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved MyPage information")
    })
    @GetMapping
    public Response<MyPageResponse> getMyPage(@CurrentUser Member member) {
        return Response.of(myPageService.getMyPage(member));
    }

    @Operation(summary = "Update MyPage information", description = "Members can update their MyPage information.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated MyPage information")
    })
    @PutMapping
    public Response<Void> updateMyPage(@CurrentUser Member member, @Valid @RequestBody MyPageRequest request) {
        myPageService.updateMyPage(member, request);
        return Response.of(HttpStatus.OK);
    }

    @Operation(summary = "Deactivate Account", description = "Members can deactivate (soft delete) their account.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully deactivated account")
    })
    @DeleteMapping
    public Response<Void> deactivateAccount(@CurrentUser Member member) {
        myPageService.deactivateAccount(member);
        return Response.of(HttpStatus.OK);
    }
}
