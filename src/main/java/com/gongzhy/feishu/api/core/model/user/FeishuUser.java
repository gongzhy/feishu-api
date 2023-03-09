package com.gongzhy.feishu.api.core.model.user;

import com.lark.oapi.service.authen.v1.model.CreateAccessTokenRespBody;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author gongzhiyuan
 * @date 2023/3/9
 */
@Data
@NoArgsConstructor
public class FeishuUser {
    private String unionId;

    private String realName;

    private String avatarUrl;

    private String mobile;

    private String email;

    private String clientIp;

    private Date loginDate;

    private Date registerDate;

    public FeishuUser(CreateAccessTokenRespBody body) {
        this.unionId = body.getUnionId();
        this.realName = body.getName();
        this.avatarUrl = body.getAvatarUrl();
        this.mobile = body.getMobile();
        this.email = body.getEmail();
    }

}