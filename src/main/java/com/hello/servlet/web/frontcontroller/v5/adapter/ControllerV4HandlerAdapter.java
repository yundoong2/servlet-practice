package com.hello.servlet.web.frontcontroller.v5.adapter;

import com.hello.servlet.web.frontcontroller.ModelView;
import com.hello.servlet.web.frontcontroller.v4.ControllerV4;
import com.hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * V4를 지원하는 어댑터
 */
public class ControllerV4HandlerAdapter implements MyHandlerAdapter {

    /**
     * 핸들러(컨트롤러)가 V4 버전인지 판별
     * @param handler {@link Object}
     * @return boolean {@link Boolean}
     */
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    /**
     * 파라미터로 받은 핸들러(컨트롤러)를 V4로 캐스팅한 후 컨트롤러를 호출한 뒤, ModelView 객체를 반환한다.
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @param handler {@link Object}
     * @return ModelView {@link ModelView}
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {

        //handler를 ControllerV4로 캐스팅
        ControllerV4 controller = (ControllerV4) handler;

        //paramMap, model을 만들어서 해당 컨트롤러로 호출
        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();

        //호출뒤 viewName을 반환 받음
        String viewName = controller.process(paramMap, model);

        ModelView mv = new ModelView(viewName);
        mv.setModel(model);

        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName,
                        request.getParameter(paramName)));

        return paramMap;
    }
}
