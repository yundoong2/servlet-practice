package com.hello.servlet.web.frontcontroller.v3;

import com.hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

/**
 * V3 컨트롤러에서 사용되는 인터페이스 정의
 */
public interface ControllerV3 {

    /**
     * 쿼리 파라미터들이 들어있는 Map을 파라미터롤 받고 ModelView를 반환한다.
     * @param paramMap {@link Map}
     * @return ModelView {@link ModelView}
     */
    ModelView process(Map<String, String> paramMap);

}
