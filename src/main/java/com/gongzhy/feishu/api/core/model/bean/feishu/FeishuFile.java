package com.gongzhy.feishu.api.core.model.bean.feishu;

import com.gongzhy.feishu.api.core.model.bean.ToStringBase;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 飞书上传文件返回对象
 */
@Data
@NoArgsConstructor
public class FeishuFile extends ToStringBase {
    private String code;
    private String url;

}
