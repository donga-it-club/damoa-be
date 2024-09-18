package com.donga.damoa.domain.myPage.application;

import com.donga.damoa.domain.member.domain.Member;
import com.donga.damoa.domain.myPage.dao.MyPageRepository;
import com.donga.damoa.domain.myPage.dto.MyPageResponse;
import com.donga.damoa.domain.myPage.error.MyPageErrorCode;
import com.donga.damoa.domain.myPage.error.exception.MyPageNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class MyPageQueryService {

    private final MyPageRepository myPageRepository;

    //MyPage 조회
    @Transactional(readOnly = true)
    public MyPageResponse getMyPage(Member member) {
        var getMyPage = myPageRepository.findByMemberId(member.getId())
            .orElseThrow(() -> new MyPageNotFoundException(MyPageErrorCode.MY_PAGE_NOT_FOUND));

        return MyPageResponse.from(member.getName(), member.getEmail(), member.getMajors());
    }
}
