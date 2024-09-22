package com.donga.damoa.domain.study.domain;

import com.donga.damoa.domain.member.domain.Member;
import com.donga.damoa.domain.model.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Study extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title; // 제목

    @Column(name = "content", nullable = false)
    private String content; // 내용

    @Column(name = "link", nullable = true)
    private String link; // 링크

    @Column(name = "recruit_deadline", nullable = false)
    private LocalDateTime recruitDeadline; // 모집 마감일

    @Enumerated(EnumType.STRING)
    @Column(name = "recruitment_status", nullable = false)
    private RecruitmentStatus status; // 모집 상태

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leader_id", nullable = false)
    private Member leader; // 리더

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "study")
    private List<MemberStudy> memberStudies = new ArrayList<>();

    @Builder
    public Study(String title, String content, String link, LocalDateTime recruitDeadline,
        Member leader) {
        this.title = title;
        this.content = content;
        this.link = link;
        this.recruitDeadline = recruitDeadline;
        this.leader = leader;
    }

    public void updateRecruitStatus(RecruitmentStatus recruitmentStatus) {
        this.status = recruitmentStatus;
    }

}
