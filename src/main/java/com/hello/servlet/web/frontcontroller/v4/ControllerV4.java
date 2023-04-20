package com.hello.servlet.web.frontcontroller.v4;

import java.util.Map;

public interface ControllerV4 {
    /**
     * @param paramMap {@link Map}
     * @param model {@link Map}
     * @return viewName {@link String}
     */
    String process(Map<String, String> paramMap, Map<String, Object> model);
}
