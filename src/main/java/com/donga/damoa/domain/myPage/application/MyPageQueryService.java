package com.donga.damoa.domain.myPage.application;

import com.donga.damoa.domain.member.dao.MemberRepository;
import com.donga.damoa.domain.member.domain.Member;
import com.donga.damoa.domain.member.dto.MajorDto;
import com.donga.damoa.domain.myPage.dao.MyPageRepository;
import com.donga.damoa.domain.myPage.dto.MyPageRequest;
import com.donga.damoa.domain.myPage.dto.MyPageResponse;
import com.donga.damoa.domain.myPage.error.MyPageErrorCode;
import com.donga.damoa.domain.myPage.error.exception.MyPageNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyPageQueryService extends MyPageServiceTemplate {

    private final MemberRepository memberRepository;
    private final MyPageRepository myPageRepository;

    @Override
    protected void validateMember(Member member) {
        // Member가 존재하지 않으면 예외를 던짐
        if (!memberRepository.existsById(member.getId())) {
            throw new IllegalArgumentException("Member not found.");
        }
    }

    @Override
    protected MyPageResponse processGetMyPage(Member member) {
        // MyPage 정보 조회
        var myPage = myPageRepository.findByMemberId(member.getId())
            .orElseThrow(() -> new MyPageNotFoundException(MyPageErrorCode.MY_PAGE_NOT_FOUND));

        // 전공 정보 처리
        var majorDtos = member.getMajors().stream()
            .map(major -> new MajorDto(major.getType().toString(), major.getName()))
            .collect(Collectors.toList());

        // MyPageResponse 반환
        return new MyPageResponse(member.getName(), member.getEmail(), majorDtos);
    }

    @Override
    protected void processUpdate(Member member, MyPageRequest request) {
        // 이 서비스에서 업데이트는 지원하지 않음
        throw new UnsupportedOperationException("This service does not support update.");
    }

    @Override
    protected void processDeactivate(Member member) {
        // 계정 비활성화 로직을 추가 (소프트 삭제 처리)
        member.deactivateAccount();  // 계정 비활성화 메소드 호출
        memberRepository.save(member);  // 변경 사항 저장
    }
}
