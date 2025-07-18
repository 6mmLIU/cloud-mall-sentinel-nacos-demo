package com.example.util;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class RequestOriginUtil implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        String reqOrigin = httpServletRequest.getHeader("req_origin");
        if (reqOrigin == null) {
            reqOrigin = " ";
        }
        return reqOrigin;
    }
}
