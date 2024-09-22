package com.donga.damoa.domain.project.domain;

import com.donga.damoa.domain.member.domain.Member;
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
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "projects")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leader_id", nullable = false)
    private Member leader; // 프로젝트 리더

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MemberProject> memberProjects = new ArrayList<>(); // 프로젝트 팀원

    @Column(name = "title", nullable = false)
    private String title; // 게시물 제목

    @Column(name = "content", nullable = false)
    private String content; // 게시물 내용

    @Column(name = "recruitment_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private RecruitmentStatus recruitmentStatus; // 모집 상태

    @Column(name = "link", nullable = true)
    private String link; // 사용자가 개별적으로 만든 신청 링크

    @Builder
    public Project(Member leader, String title, String content, String link) {
        this.leader = leader;
        this.title = title;
        this.content = content;
        this.link = link;
    }

    // 모집 상태 변경
    public void updateRecruitmentStatus(RecruitmentStatus recruitmentStatus) {
        this.recruitmentStatus = recruitmentStatus;
    }

}
