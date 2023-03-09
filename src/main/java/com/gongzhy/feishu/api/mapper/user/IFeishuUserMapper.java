package com.gongzhy.feishu.api.mapper.user;

import com.gongzhy.feishu.api.core.model.user.FeishuUser;

/**
 * @author gongzhiyuan
 * @date 2023/3/9
 */
public interface IFeishuUserMapper {

    /**
     * @param record
     * @return
     */
    int insert(FeishuUser record);

    /**
     * @param unionId
     * @return
     */
    FeishuUser get(String unionId);

    /**
     * @param record
     * @return
     */
    int update(FeishuUser record);
}