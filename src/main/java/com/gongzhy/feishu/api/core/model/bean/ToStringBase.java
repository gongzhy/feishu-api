package com.gongzhy.feishu.api.core.model.bean;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;

import java.io.Serializable;


@SuppressWarnings("serial")
public class ToStringBase implements Serializable {

    @Override
    public String toString() {
        return JSON.toJSONString(this,
                JSONWriter.Feature.WriteNullListAsEmpty,
                JSONWriter.Feature.WriteNullStringAsEmpty,
                JSONWriter.Feature.WriteNullNumberAsZero,
                JSONWriter.Feature.WriteNullBooleanAsFalse,
                JSONWriter.Feature.WriteMapNullValue);
    }
}
