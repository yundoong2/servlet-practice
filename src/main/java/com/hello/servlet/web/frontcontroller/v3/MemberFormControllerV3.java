package com.hello.servlet.web.frontcontroller.v3;

import com.hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

/**
 * 회원 저장 폼에 대한 컨트롤러
 */
public class MemberFormControllerV3 implements ControllerV3{

    /**
     * 논리 뷰 이름을 담은 ModelView 객체를 반환한다.
     * @param paramMap {@link Map}
     * @return ModelView {@link ModelView}
     */
    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
    }
}
