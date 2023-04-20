package com.hello.servlet.web.frontcontroller.v5;

import com.hello.servlet.web.frontcontroller.ModelView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 어댑터 구현을 위한 인터페이스
 */
public interface MyHandlerAdapter {
    
    //어댑터가 해당 핸들러(컨트롤러)를 처리할 수 있는지 판별하는 메소드
    boolean supports(Object handler);

    //실제 컨트롤러를 호출하고, ModelView를 반환하는 메소드
    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws ServletException, IOException;
}
