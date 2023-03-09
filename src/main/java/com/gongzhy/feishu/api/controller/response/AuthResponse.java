package com.gongzhy.feishu.api.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gongzhiyuan
 * @date 2023/3/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String redirectUrl;
    private String code;

}
