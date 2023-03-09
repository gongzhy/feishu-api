package com.gongzhy.feishu.api.service.helper.feishu;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.gongzhy.feishu.api.core.exception.ServerException;
import com.gongzhy.feishu.api.core.result.ResultCode;
import com.gongzhy.feishu.api.service.helper.CardHelper;
import com.gongzhy.feishu.api.service.helper.FeishuBaseHelper;
import com.lark.oapi.service.im.v1.enums.CreateMessageReceiveIdTypeEnum;
import com.lark.oapi.service.im.v1.model.*;
import lombok.extern.slf4j.Slf4j;

/**
 * 飞书卡片内容生成器
 *
 * @author gongzhiyuan
 * @time 2020年4月22日
 */
@Slf4j
public class FeishuMessageHelper extends FeishuBaseHelper {


    /**
     * 发送卡片消息(用于发送群通知，和回复消息)
     *
     * @param receiveIdType
     * @param receiveId
     * @param header
     * @param elements
     */
    public static void send(CreateMessageReceiveIdTypeEnum receiveIdType, String receiveId, JSONObject header, JSONArray elements) {
        try {
            //
            //
            JSONObject card = CardHelper.createCard(header, elements);
            //
            CreateMessageReqBody reqBody = CreateMessageReqBody.newBuilder()
                    .receiveId(receiveId)
                    .msgType("interactive")
                    .content(card.toJSONString())
                    .build();
            //
            CreateMessageReq req = CreateMessageReq.newBuilder()
                    .receiveIdType(receiveIdType)
                    .createMessageReqBody(reqBody)
                    .build();
            //
            CreateMessageResp resp = client.im().message().create(req);
            log.info("FESISHU RESONSE, {}", JSONObject.toJSONString(resp));
        } catch (Exception e) {
            log.error("[FEISHU] send message exception, error : {}", e);
        }
    }

    /**
     * 回复消息
     *
     * @param messageId
     * @param header
     * @param elements
     */
    public static void reply(String messageId, JSONObject header, JSONArray elements) {
        try {

            ReplyMessageReqBody reqBody = ReplyMessageReqBody.newBuilder()
                    .msgType("interactive")
                    .content(CardHelper.createCard(header, elements).toJSONString())
                    .build();
            //
            ReplyMessageReq req = ReplyMessageReq.newBuilder()
                    .messageId(messageId)
                    .replyMessageReqBody(reqBody)
                    .build();

            client.im().message().reply(req);
        } catch (Exception e) {
            log.error("[FEISHU] reply message exception, error : {}", e);
        }
    }


    /**
     * 通过回话ID，获取会话名字
     *
     * @param chatId
     * @throws ServerException
     */
    public static String getChatName(String chatId) throws ServerException {
        try {
            GetChatReq req = GetChatReq.newBuilder().chatId(chatId).build();
            GetChatResp resp = client.im().chat().get(req);
            return resp.getData().getName();
        } catch (Exception e) {
            log.error("[FEISHU] get chat name exception, error : {}", e);
            throw new ServerException(ResultCode.FEISHU_FAIL);
        }
    }


}
