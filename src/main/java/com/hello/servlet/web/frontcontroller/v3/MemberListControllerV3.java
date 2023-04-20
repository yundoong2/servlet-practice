package com.hello.servlet.web.frontcontroller.v3;

import com.hello.servlet.domain.member.Member;
import com.hello.servlet.domain.member.MemberRepository;
import com.hello.servlet.web.frontcontroller.ModelView;

import java.util.List;
import java.util.Map;

/**
 * 회원 목록 조회 컨트롤러
 */
public class MemberListControllerV3 implements ControllerV3{
    private MemberRepository memberRepository = MemberRepository.getInstance();

    /**
     * Repository를 통해 모든 회원을 조회 후, List 데이터를 모델에 담은 뒤
     * 논리 뷰 이름과 함께 ModelView 객체를 반환한다.
     * @param paramMap {@link Map}
     * @return ModelView {@link ModelView}
     */
    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();

        ModelView mv = new ModelView("members");
        mv.getModel().put("members", members);

        return mv;
    }
}
