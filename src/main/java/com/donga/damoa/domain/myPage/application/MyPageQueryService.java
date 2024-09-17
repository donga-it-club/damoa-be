package com.donga.damoa.domain.myPage.application;

import com.donga.damoa.domain.member.dao.MemberRepository;
import com.donga.damoa.domain.member.domain.Member;
import com.donga.damoa.domain.member.dto.MajorDto;
import com.donga.damoa.domain.myPage.dao.MyPageRepository;
import com.donga.damoa.domain.myPage.dto.MyPageRequest;
import com.donga.damoa.domain.myPage.dto.MyPageResponse;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageQueryService extends MyPageServiceTemplate {

    private final MemberRepository memberRepository;
    private final MyPageRepository myPageRepository;

    @Override
    protected void validateMember(Member member) {
        if (memberRepository.existsById(member.getId())) {
            throw new IllegalArgumentException("Member not found.");
        }
    }

    @Override
    protected MyPageResponse processGetMyPage(Member member) {
        // MyPage 정보 조회
        var myPage = myPageRepository.findByMemberId(member.getId())
            .orElseThrow(() -> new IllegalArgumentException("MyPage not found."));

        var majorDtos = member.getMajors().stream()
            .map(major -> new MajorDto(major.getType().toString(), major.getName()))
            .collect(Collectors.toList());

        return new MyPageResponse(member.getName(), member.getEmail(), majorDtos);
    }

    @Override
    protected void processUpdate(Member member, MyPageRequest request) {
        throw new UnsupportedOperationException("This service does not support update.");
    }

    @Override
    protected void processDeactivate(Member member) {
        throw new UnsupportedOperationException("This service does not support deactivate.");
    }
}