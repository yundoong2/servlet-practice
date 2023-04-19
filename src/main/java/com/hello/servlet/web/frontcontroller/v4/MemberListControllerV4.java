package com.hello.servlet.web.frontcontroller.v4;

import com.hello.servlet.domain.member.Member;
import com.hello.servlet.domain.member.MemberRepository;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4{

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        List<Member> members = memberRepository.findAll();
        model.put("members", members);

        return "members";
    }
}
