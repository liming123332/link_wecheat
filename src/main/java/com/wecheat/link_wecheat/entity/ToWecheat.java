package com.wecheat.link_wecheat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToWecheat {
    String content;
    int createTime;
    String toUserName;
    String fromUserName;
    String msgType;
    Long msgId;

}
