package com.gongzhy.feishu.api.service.helper.feishu;

import com.gongzhy.feishu.api.core.exception.ServerException;
import com.gongzhy.feishu.api.core.model.user.FeishuUser;
import com.gongzhy.feishu.api.core.result.ResultCode;
import com.gongzhy.feishu.api.service.helper.FeishuBaseHelper;
import com.lark.oapi.service.authen.v1.model.CreateAccessTokenReq;
import com.lark.oapi.service.authen.v1.model.CreateAccessTokenReqBody;
import com.lark.oapi.service.authen.v1.model.CreateAccessTokenResp;
import com.lark.oapi.service.authen.v1.model.CreateAccessTokenRespBody;
import com.lark.oapi.service.contact.v3.model.GetUserReq;
import com.lark.oapi.service.contact.v3.model.GetUserResp;
import com.lark.oapi.service.contact.v3.model.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FeishuUserHelper extends FeishuBaseHelper {

    private static final String GRANT_TYPE = "authorization_code";

    /**
     * @param code
     * @return
     * @throws ServerException
     */
    public static FeishuUser auth(String code) throws ServerException {
        //
        try {
            // 创建请求对象
            CreateAccessTokenReq req = CreateAccessTokenReq.newBuilder().createAccessTokenReqBody(
                    CreateAccessTokenReqBody.newBuilder().grantType(GRANT_TYPE).code(code).build())
                    .build();
            // 发起请求
            // 如开启了Sdk的token管理功能，就无需调用 RequestOptions.newBuilder().tenantAccessToken("t-xxx").build()来设置租户token了
            CreateAccessTokenResp resp = client.authen().accessToken().create(req);
            CreateAccessTokenRespBody body = resp.getData();
            if (body == null) {
                throw new ServerException(ResultCode.FEISHU_FAIL, "获取用户信息异常");
            }
            return new FeishuUser(body);
        } catch (Exception e) {
            log.error("[FEISHU]auth exception, error : {}", e);
            if (e instanceof ServerException) {
                throw (ServerException) e;
            }
            //
            e.printStackTrace();
            throw new ServerException(ResultCode.FEISHU_FAIL, "获取用户信息异常");
        }
    }

    /**
     * @param userId
     */
    public static User get(String userId, String userIdType) throws ServerException {
        //
        try {
            GetUserReq req = GetUserReq.newBuilder().userId(userId).userIdType(userIdType).build();
            GetUserResp resp = client.contact().user().get(req);
            return resp.getData().getUser();
        } catch (Exception e) {
            log.error("[FEISHU] get approval exception, error : {}", e);
            e.printStackTrace();
            throw new ServerException(ResultCode.FEISHU_FAIL, "获取用户信息异常");
        }
    }

}
