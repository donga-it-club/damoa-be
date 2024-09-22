package com.donga.damoa.domain.myPage.domain;

import com.donga.damoa.domain.member.domain.EnrollmentStatus;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "my_pages")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyPage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "my_page_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "enrollment_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EnrollmentStatus enrollmentStatus;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Project> projects;

    @Builder
    public MyPage(Member member, EnrollmentStatus enrollmentStatus, List<Project> projects) {
        this.member = member;
        this.enrollmentStatus = enrollmentStatus;
        this.projects = projects;
    }

    public void updateEnrollmentStatus(EnrollmentStatus newStatus) {
        this.enrollmentStatus = newStatus;
    }
}
