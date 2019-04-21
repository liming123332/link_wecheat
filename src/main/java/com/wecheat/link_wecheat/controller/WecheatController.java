package com.wecheat.link_wecheat.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.xml.XmlMapper;
import com.wecheat.link_wecheat.entity.FromWecheat;
import com.wecheat.link_wecheat.entity.ToWecheat;
import com.wecheat.link_wecheat.entity.Xml;
import com.wecheat.link_wecheat.uitls.Sha1Utils;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/wecheat")
public class WecheatController {

    private  String token="mytoken";

    @GetMapping("/welcome")
    public String welcome(String signature,String timestamp,String nonce,String echostr){
        System.out.println("welcome");
        List<String> list=new ArrayList();
        list.add(token);
        list.add(timestamp);
        list.add(nonce);
        Collections.sort(list);
        StringBuilder stringBuilder=new StringBuilder();
        for (String s : list) {
            System.out.println(s);
            stringBuilder.append(s);
        }
            String sha1Echostr= Sha1Utils.encode(stringBuilder.toString());
            System.out.println(sha1Echostr);
            System.out.println(signature);
            if(signature.equals(sha1Echostr)){
                return echostr;
            }

        return null;
    }

    @PostMapping("/welcome")
    public String welcome(@RequestBody String content){
        System.out.println(content);
        JSONObject xmljsonObject = XML.toJSONObject(content);
        String s = xmljsonObject.toString(4);
        //xml字符串转json字符串 从第7开始截取
        String substring = s.substring(7, s.length() - 1);
        ToWecheat wecheat = JSON.parseObject(substring, ToWecheat.class);
        System.out.println(wecheat);
        //TODO
        Xml xml=new Xml();
        xml.setToUserName("![CDATA["+wecheat.getFromUserName()+"]");
        xml.setFromUserName("![CDATA["+wecheat.getToUserName()+"]");
        xml.setCreateTime(1555751603);
        xml.setMsgType("![CDATA["+"text"+"]");
        xml.setContent("![CDATA["+"你好"+"]");
        FromWecheat fromWecheat=new FromWecheat(xml);
        XmlMapper xmlMapper=new XmlMapper();
        return null;
    }
}
