package com.wecheat.link_wecheat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Xml {
    String toUserName;
    String fromUserName;
    int createTime;
    String msgType;
    String content;
}
