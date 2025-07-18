package com.example.advice;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {
    //sentinel抛出500异常
    @ExceptionHandler(BlockException.class)
    public Object handleBlockException(BlockException e) {
        R r = null;
        if (e instanceof FlowException) {
            // 限流异常
            r = R.failed("限流异常");
        } else if (e instanceof DegradeException) {
            // 降级异常
            r = R.failed("降级异常");
        } else if (e instanceof ParamFlowException) {
            // 热点参数异常
            r = R.failed("热点参数异常");
        } else if (e instanceof AuthorityException) {
            // 授权异常
            r = R.failed("授权异常");
        } else if (e instanceof SystemBlockException) {
            // 系统异常
            r = R.failed("系统异常");
        }
        return r;
    }
}
