package com.hello.servlet.web.frontcontroller.v5;

import com.hello.servlet.web.frontcontroller.ModelView;
import com.hello.servlet.web.frontcontroller.MyView;
import com.hello.servlet.web.frontcontroller.v3.MemberFormControllerV3;
import com.hello.servlet.web.frontcontroller.v3.MemberListControllerV3;
import com.hello.servlet.web.frontcontroller.v3.MemberSaveControllerV3;
import com.hello.servlet.web.frontcontroller.v4.MemberFormControllerV4;
import com.hello.servlet.web.frontcontroller.v4.MemberListControllerV4;
import com.hello.servlet.web.frontcontroller.v4.MemberSaveControllerV4;
import com.hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import com.hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * /frontcontroller/v5/* 형식의 URL로 HTTP 요청이 들어오면 처리하는 Servlet
 */
@WebServlet(name = "frontControllerServletV5", urlPatterns = "/frontcontroller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    //핸들러들을 담아두는 Map
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    //어댑터들을 담아두는 List
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    /**
     * 처리할 대상 핸들러들을 Map에 담아둔다.
     */
    private void initHandlerMappingMap() {
        //V3
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        //V4
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    /**
     * 사용할 어댑터들을 List에 담아둔다.
     */
    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter()); //V3
        handlerAdapters.add(new ControllerV4HandlerAdapter()); //V4
    }

    /**
     * HTTP 요청에 대해 핸들러와 어댑터를 가지고 로직을 처리하는 메소드
     * @param request the {@link HttpServletRequest} object that contains the request the client made of the servlet
     *
     * @param response the {@link HttpServletResponse} object that contains the response the servlet returns to the client
     *
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //request에서 대상 핸들러를 조회한다.
        Object handler = getHandler(request);
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //어댑터 목록에서 해당 핸들러를 처리할 수 있는 어댑터를 찾는다.
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        //해당 어댑터에 handler를 파라미터로 넘겨서 컨트롤러를 호출하고 ModelView 를 반환받는다.
        ModelView mv = adapter.handle(request, response, handler);

        //반환 받은 ModelView에서 viewName을 가지고 MyView 객체를 반환받는다.
        MyView view = viewResolver(mv.getViewName());

        //ModelView 담겨 있는 model을 파라미터로 넘겨 View를 렌더링한다.
        view.render(mv.getModel(), request, response);
    }

    /**
     * request 파라미터에서 URI을 조회하여 대상 핸들러를 Object로 반환한다.
     * @param request {@link HttpServletRequest}
     * @return
     */
    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    /**
     * handlerAdapters 에서 파라미터로 받은 handler 를 지원하는 어댑터를 찾는다.
     * <p>
     * 지원하는 어댑터가 있는 경우 해당 어댑터를 반환하고, 없는 경우 IllegalArgumentException 예외를 발생시킨다.
     * @param handler {@link Object}
     * @return MyHandlerAdapter {@link MyHandlerAdapter}
     */
    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler=" + handler);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
