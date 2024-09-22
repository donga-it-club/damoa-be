package com.donga.damoa.project.service.util;

import com.donga.damoa.domain.member.domain.Member;
import com.donga.damoa.domain.project.domain.Project;

public class TestCreateProjectFactory {

    public static Project createProject(String title, String content, String link, Member leader) {
        return new Project(leader, title, content, link);
    }

}
