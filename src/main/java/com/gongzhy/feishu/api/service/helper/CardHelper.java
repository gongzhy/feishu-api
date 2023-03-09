package com.gongzhy.feishu.api.service.helper;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * 飞书卡片内容生成器
 *
 * @author gongzhiyuan
 * @time 2020年4月22日
 */
@Slf4j
public class CardHelper extends FeishuBaseHelper {

    /**
     * 组装header
     *
     * @param title
     * @param color
     * @return
     */
    public static JSONObject getHeader(String title, String color) {
        JSONObject titleObj = new JSONObject();
        titleObj.put("tag", "plain_text");
        titleObj.put("content", title);
        JSONObject header = new JSONObject();
        header.put("title", titleObj);
        if (StringUtils.isNotBlank(color)) {
            header.put("template", color);
        }
        return header;
    }

    /**
     * 组装text
     *
     * @param content
     * @return
     */
    public static JSONObject getText(String content) {
        JSONObject element = new JSONObject();
        element.put("tag", "div");
        JSONObject text = new JSONObject();
        text.put("tag", "lark_md");
        text.put("content", content);
        element.put("text", text);
        return element;
    }

    /**
     * 组装fields
     *
     * @param contents
     * @return
     */
    public static JSONObject getField(String... contents) {
        JSONObject element = new JSONObject();
        JSONArray fields = new JSONArray();
        for (int i = 0; i < contents.length; i++) {
            JSONObject text = getText(contents[i]);
            text.put("is_short", false);
            fields.add(text);
        }
        element.put("fields", fields);
        element.put("tag", "div");
        return element;
    }

    /**
     * @param contents
     * @return
     */
    public static JSONObject getNote(String... contents) {
        JSONObject element = new JSONObject();
        JSONArray elements = new JSONArray();
        for (int i = 0; i < contents.length; i++) {
            JSONObject text = new JSONObject();
            text.put("tag", "lark_md");
            text.put("content", contents[i]);
            elements.add(text);
        }
        element.put("elements", elements);
        element.put("tag", "note");
        return element;
    }


    /**
     * 获取行为
     *
     * @param actions
     * @return
     */
    public static JSONObject getAction(JSONObject... actions) {
        JSONObject element = new JSONObject();
        element.put("tag", "action");
        element.put("actions", actions);
        return element;
    }

    /**
     * @param buttonText
     * @param value
     * @return
     */
    public static JSONObject getButton(String buttonText, JSONObject value) {
        JSONObject action = new JSONObject();
        action.put("tag", "button");
        JSONObject text = new JSONObject();
        text.put("tag", "plain_text");
        text.put("content", buttonText);
        action.put("text", text);
        action.put("value", value);
        action.put("type", "primary");
        return action;
    }

    /**
     * @param buttonText
     * @param bottonLink
     * @return
     */
    public static JSONObject getButton(String buttonText, String bottonLink) {
        JSONObject action = new JSONObject();
        action.put("tag", "button");
        JSONObject text = new JSONObject();
        text.put("tag", "plain_text");
        text.put("content", buttonText);
        action.put("text", text);
        action.put("url", bottonLink);
        action.put("type", "primary");
        return action;
    }

    /**
     * 获取分割线
     *
     * @return
     */
    public static JSONObject getHr() {
        JSONObject element = new JSONObject();
        element.put("tag", "hr");
        return element;
    }


    /**
     * 生成卡片
     *
     * @param header
     * @param elements
     * @return
     */
    public static JSONObject createCard(JSONObject header, JSONArray elements) {
        JSONObject card = new JSONObject();
        // header
        if (header != null) {
            card.put("header", header);
        }
        // elements
        card.put("elements", elements);
        return card;

    }


}
