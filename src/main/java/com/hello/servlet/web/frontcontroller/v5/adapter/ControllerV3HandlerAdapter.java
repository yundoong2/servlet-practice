package com.hello.servlet.web.frontcontroller.v5.adapter;

import com.hello.servlet.web.frontcontroller.ModelView;
import com.hello.servlet.web.frontcontroller.v3.ControllerV3;
import com.hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.apache.taglibs.standard.lang.jstl.ImplicitObjects.createParamMap;

/**
 * V3 버전을 지원하는 어댑터
 */
public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    
    /**
     * 핸들러(컨트롤러)가 V3 버전인지 판별
     * @param handler {@link Object}
     * @return boolean {@link Boolean}
     */
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
    }

    /**
     * 파라미터로 받은 핸들러(컨트롤러)를 V3로 캐스팅한 후 컨트롤러를 호출한 뒤, ModelView 객체를 반환한다.
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @param handler {@link Object}
     * @return ModelView {@link ModelView}
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV3 controller = (ControllerV3) handler;

        Map<String, String> paramMap = createParamMap(request);

        ModelView mv = controller.process(paramMap);
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
