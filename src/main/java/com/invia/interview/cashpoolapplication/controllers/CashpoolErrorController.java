package com.invia.interview.cashpoolapplication.controllers;

import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class CashpoolErrorController implements ErrorController {

    private static Logger logger = LoggerFactory.getLogger(CashpoolErrorController.class);
    private ErrorAttributes errorAttributes;
    private final static String ERROR_PATH = "/error";

    public CashpoolErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping(value = ERROR_PATH, produces = "text/html")
    public ModelAndView errorHtml(HttpServletRequest request) {
        HttpStatus status = getStatus(request);

        if(this.getClass().getResource("/templates/errorhandlers/" + status + ".ftl") != null) {
            return new ModelAndView("errorhandlers/" + status, getErrorAttributes(request, false));
        }
        else {
            return new ModelAndView("errorhandlers/500",getErrorAttributes(request,false));
        }
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request,
                                                   boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return this.errorAttributes.getErrorAttributes(requestAttributes,
                includeStackTrace);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        if (statusCode != null) {
            try {
                return HttpStatus.valueOf(statusCode);
            } catch (Exception ex) {
            }
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
