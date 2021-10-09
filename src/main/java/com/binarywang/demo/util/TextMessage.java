package com.binarywang.demo.util;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * @Description: java类作用描述
 * @Author: zhaoxc
 * @CreateDate: 2021/10/9$ 13:42$
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */

@Data
public class TextMessage {
    @XStreamAlias("FromUser")
    private String fromUser;
    private String toUser;
    private String createTime;
    private String content;

}
