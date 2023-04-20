package com.hello.servlet.web.frontcontroller.v3;

import com.hello.servlet.web.frontcontroller.ModelView;
import com.hello.servlet.web.frontcontroller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    /**
     * 프론트 컨트롤러 초기화 시, 사용되는 컨트롤러들을 Map에 저장해둔다.
     */
    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    /**
     * HTTP 요청이 들어오면 서블릿에서 해당 service 메소드가 실행된다.
     * @param request the {@link HttpServletRequest} object that contains the request the client made of the servlet
     *
     * @param response the {@link HttpServletResponse} object that contains the response the servlet returns to the client
     *
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        //URI를 확인하여 해당되는 컨트롤러를 가져온다.
        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //request에 들어있는 쿼리 파라미터를 Map에 모두 집어넣는다.
        Map<String, String> paramMap = createParamMap(request);

        //쿼리 파라미터가 들어있는 paramMap을 파라미터로 컨트롤러를 호출한다.
        ModelView mv = controller.process(paramMap);

        //컨트롤러로부터 반환받은 View Name(논리 뷰 이름)을 가져온다.
        String viewName = mv.getViewName();
        //ViewResolver를 통해 실제 물리 뷰 이름을 가져온 후 MyView를 반환한다.
        MyView view = viewResolver(viewName);
        //반환된 컨트롤러에서 받은 Model과 request, response를 파라미터로 render()를 호출한다.
        view.render(mv.getModel(), request, response);
    }

    /**
     * HttpServletRequest에 들어있는 쿼리 파라미터들을 Map에 저장한다.
     * @param request {@link HttpServletRequest}
     * @return Map<String, String> {@link Map<>}
     */
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();

        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));

        return paramMap;
    }

    /**
     * 논리 뷰 이름을 파라미터로 받아 실제 물리 뷰 이름을 반환한다.
     * @param viewName {@link String}
     * @return MyView {@link MyView}
     */
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
