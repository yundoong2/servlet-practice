package com.hello.servlet.web.frontcontroller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;


/**
 * 뷰로 렌더링 해주는 클래스
 */
public class MyView {
    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    /**
     * viewPath를 받아 RequestDispatcher 생성 후 뷰로 이동
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @throws ServletException
     * @throws IOException
     */
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    /**
     * 컨트롤러에서 받은 Model 객체를 파라미터로 받아 request에 SetAttribute 해줌
     * @param model {@link Map}
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @throws ServletException
     * @throws IOException
     */
    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        modelToRequestAttribute(model, request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    /**
     * Model에 있는 값들을 request에 setAttribute로 추가해줌
     * @param model {@link Map}
     * @param request {@link HttpServletRequest}
     */
    private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        model.forEach((key, value) -> request.setAttribute(key, value));
    }
}
