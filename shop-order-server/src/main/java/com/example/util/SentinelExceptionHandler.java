package com.example.util;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.api.R;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SentinelExceptionHandler implements BlockExceptionHandler {
    /**
     * 能捕获到sentinel排除的异常
     * 参数1:请求对象
     * 参数2: 应答对象
     * 参数3: 发生的异常(父类的异常类型传的是子类的异常对象)
     */
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        R r=null;
        if (e instanceof FlowException) {
            //限流异常
           r= R.failed("限流异常");
        } else if (e instanceof DegradeException) {
            //降级异常
            r= R.failed("降级异常");
        } else if (e instanceof ParamFlowException) {
            //热点参数异常
            r= R.failed("热点参数异常");
        } else if (e instanceof AuthorityException) {
            //授权异常
            r= R.failed("授权异常");
        } else if (e instanceof SystemBlockException) {
            //系统异常
            r= R.failed("系统异常");
        }
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        String json = JSON.toJSONString(r);
        httpServletResponse.getWriter().write(json);
    }
}
