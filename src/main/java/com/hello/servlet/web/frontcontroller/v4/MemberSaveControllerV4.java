package com.hello.servlet.web.frontcontroller.v4;

import com.hello.servlet.domain.member.Member;
import com.hello.servlet.domain.member.MemberRepository;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4{

    private MemberRepository memberRepository = MemberRepository.getInstance();

    /**
     * 파라미터에 model을 받아서 저장한 회원 객체를 model에 담는다.
     * @param paramMap {@link Map}
     * @param model {@link Map}
     * @return String {@link String}
     */
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.put("member", member);

        return "save-result";
    }
}
