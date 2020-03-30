package com.wonders.global;
/**
 * 定义/error输出页面
 * 创建日期：2017-12-29下午4:29:19
 * author:wuzhiheng
 */

import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class CustomErrorController implements ErrorController {
    private static final String ERROR_PATH = "error";
    private ErrorAttributes errorAttributes;

    public CustomErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping(value=ERROR_PATH)
    public String handleError(HttpServletRequest request, Model model){
        ServletWebRequest requestAttributes =  new ServletWebRequest(request);
        Map<String, Object> attr = this.errorAttributes.getErrorAttributes(requestAttributes, false);
        model.addAttribute("status",attr.get("status"));
        model.addAttribute("message",attr.get("message"));
        return "pages/error";
    }
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
