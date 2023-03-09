package com.gongzhy.feishu.api.controller;

import com.gongzhy.feishu.api.controller.request.impl.AuthRequest;
import com.gongzhy.feishu.api.controller.response.AuthResponse;
import com.gongzhy.feishu.api.core.exception.ServerException;
import com.gongzhy.feishu.api.core.model.user.FeishuUser;
import com.gongzhy.feishu.api.core.result.ResultBean;
import com.gongzhy.feishu.api.mapper.user.IFeishuUserMapper;
import com.gongzhy.feishu.api.service.helper.feishu.FeishuUserHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 机器人通知
 */
@Slf4j
@RestController
@RequestMapping("/api/feishu")
public class FeishuAuthController {

    @Autowired
    private IFeishuUserMapper feishuUserMapper;

    /**
     * 发送飞书消息
     *
     * @return
     */
    @PostMapping("/auth")
    public ResultBean send(@RequestBody AuthRequest authRequest, @RequestAttribute(name = "clientIp") String clientIp) throws ServerException {
        //
        authRequest.checkParams();
        //
        FeishuUser feishuUser = FeishuUserHelper.auth(authRequest.getCode());
        feishuUser.setClientIp(clientIp);
        handleFeishuUser(feishuUser);
        //
        return ResultBean.success(new AuthResponse("aaa", "bbb"));
    }

    /**
     * @param feishuUser
     */
    private void handleFeishuUser(FeishuUser feishuUser) {
        int ret = feishuUserMapper.update(feishuUser);
        if (ret == 0) {
            feishuUserMapper.insert(feishuUser);
        }
    }

}
