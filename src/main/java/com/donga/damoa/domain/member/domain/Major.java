package com.donga.damoa.domain.member.domain;

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
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "majors")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Major {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "major_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private MajorType type; // 주전공 or 부전공

    private String name; // 학과 이름

    @Builder
    public Major(Member member, MajorType type, String name) {
        this.member = member;
        this.type = type;
        this.name = name;
    }

    public void assignMember(Member member) {
        this.member = member;
    }

}
