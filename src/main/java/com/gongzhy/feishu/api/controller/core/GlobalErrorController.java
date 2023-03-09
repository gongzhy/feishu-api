package com.gongzhy.feishu.api.controller.core;

import com.gongzhy.feishu.api.core.result.ResultBean;
import com.gongzhy.feishu.api.core.result.ResultCode;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class GlobalErrorController implements ErrorController {

    /**
     * 处理异常
     *
     * @param request
     * @return
     */
    @RequestMapping("/error")
    @ResponseBody
    public ResultBean handleError(HttpServletRequest request, HttpServletResponse response) {
        int status = response.getStatus();
        if (status == 404) {
            return ResultBean.failure(ResultCode.API_NOT_EXIST);
        } else if (status == 500) {
            return ResultBean.failure(ResultCode.API_NOT_EXIST);
        } else {
            return ResultBean.failure(ResultCode.API_NOT_EXIST);
        }
    }


    /**
     * 获取error路径
     *
     * @return
     */
    public String getErrorPath() {
        return "/error";
    }
}
