package com.donga.damoa.domain.member.domain;

import com.donga.damoa.domain.model.BaseEntity;
import com.donga.damoa.domain.myPage.domain.AccountStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email; // 이메일

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name; // 이름

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "member")
    private List<Major> majors = new ArrayList<>(); // 전공(학과)

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private EnrollmentStatus enrollmentStatus; // 재학 여부

    @Column(nullable = true)
    private String prLink; // PR 링크

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus accountStatus;

    @Builder
    public Member(String email, String password, String name,
        EnrollmentStatus enrollmentStatus, String prLink, AccountStatus accountStatus) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.enrollmentStatus = enrollmentStatus;
        this.prLink = prLink;
        this.role = Role.USER; // 기본적으로 USER 권한을 부여한다.
        this.accountStatus = accountStatus;
    }

    public void addMajor(Major major) {
        this.majors.add(major);
        major.assignMember(this);
    }

    public void setEnrollmentStatus(EnrollmentStatus enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void deactivateAccount() {
        this.accountStatus = AccountStatus.DEACTIVATED;
    }
}
