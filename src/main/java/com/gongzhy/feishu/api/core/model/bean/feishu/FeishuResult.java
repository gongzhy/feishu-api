package com.gongzhy.feishu.api.core.model.bean.feishu;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FeishuResult<T> {
    private int code;
    private String msg;
    private T data;

    public FeishuResult(T data) {
        this.code = 0;
        this.msg = "success!";
        this.data = data;
    }
}