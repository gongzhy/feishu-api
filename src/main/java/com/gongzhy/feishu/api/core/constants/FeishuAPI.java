package com.gongzhy.feishu.api.core.constants;

/**
 * @auth Ryan
 * @date 2021/12/26 10:26
 */
public class FeishuAPI {

    public static final String TENANT_ACCESS_TOKEN_URL = "https://open.feishu.cn/open-apis/auth/v3/tenant_access_token/internal/";
    // 批量发送消息 https://open.feishu.cn/document/ukTMukTMukTM/ucDO1EjL3gTNx4yN4UTM
    public static final String MESSAGE_V1_URL = "https://open.feishu.cn/open-apis/message/v4/batch_send/";
    // 上传文件 https://open.feishu.cn/document/ukTMukTMukTM/uUDOyUjL1gjM14SN4ITN
    public static final String UPLOAD_APPROVAL_URL = "https://www.feishu.cn/approval/openapi/v2/file/upload";

    // 创建审批 https://open.feishu.cn/document/uAjLw4CM/ukTMukTMukTM/reference/approval-v4/instance/create
    public static final String CREATE_APPROVAL_URL = "https://open.feishu.cn/open-apis/approval/v4/instances";

    // 发送消息 https://open.feishu.cn/document/uAjLw4CM/ukTMukTMukTM/reference/im-v1/message/create
    private static final String MESSAE_V2_URL = "https://open.feishu.cn/open-apis/im/v1/messages?receive_id_type=%s";
    // 回复消息 https://open.feishu.cn/document/uAjLw4CM/ukTMukTMukTM/reference/im-v1/message/reply
    private static final String REPLY_URL = "https://open.feishu.cn/open-apis/im/v1/messages/%s/reply";

    private static final String GET_GROUP_URL = "https://open.feishu.cn/open-apis/im/v1/chats/%s";
    // 查看审批定义 https://open.feishu.cn/document/uAjLw4CM/ukTMukTMukTM/reference/approval-v4/approval/get
    private static final String GET_APPROVAL_URL = "https://open.feishu.cn/open-apis/approval/v4/approvals/%s";

    // https://open.feishu.cn/document/uAjLw4CM/ukTMukTMukTM/reference/contact-v3/user/get
    private static final String GET_USER_URL = "https://open.feishu.cn/open-apis/contact/v3/users/%s";


}
