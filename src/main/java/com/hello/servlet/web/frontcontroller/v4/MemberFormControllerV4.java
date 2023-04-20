package com.hello.servlet.web.frontcontroller.v4;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4{

    /**
     * V3와 비교했을 때, 파라미터로 model을 추가로 받는다.
     * 반환 값은 String으로 논리적 뷰 이름만 반환하면 된다.
     * @param paramMap {@link Map}
     * @param model {@link Map}
     * @return String {@link String}
     */
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        return "new-form";
    }
}
