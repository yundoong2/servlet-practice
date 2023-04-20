package com.hello.servlet.web.frontcontroller.v3;

import com.hello.servlet.domain.member.Member;
import com.hello.servlet.domain.member.MemberRepository;
import com.hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

/**
 * 회원 저장 컨트롤러
 */
public class MemberSaveControllerV3 implements ControllerV3{

    private MemberRepository memberRepository = MemberRepository.getInstance();

    /**
     * paramMap에 있는 쿼리 파라미터를 조회하여 회원을 저장하고
     * 저장한 회원 객체(Member)를 Map에 담고 논리 뷰 이름과 함께 ModelView 객체를 반환한다.
     * @param paramMap {@link Map}
     * @return ModelView {@link ModelView}
     */
    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member);

        return mv;
    }
}
