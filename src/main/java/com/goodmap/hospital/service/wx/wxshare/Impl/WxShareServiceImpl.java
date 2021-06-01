package com.goodmap.hospital.service.wx.wxshare.Impl;

import com.goodmap.hospital.common.utils.WxUtil;
import com.goodmap.hospital.service.wx.wxshare.WxShareService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 刘智强
 * @date 2021/1/18
 * @Description
 */
@Service
public class WxShareServiceImpl implements WxShareService {

    @Getter
    private static String AppId;
    @Value("${wx.appId}")
    public void setAppId(String appId) {
        AppId = appId;
    }

    @Override
    public Map initJSSDKConfig(String url) throws Exception{
        //获取AccessToken
        String accessToken = WxUtil.getJSSDKAccessToken();
        //获取jssdkGetticket
        String jsapiTicket = WxUtil.getJssdkGetticket(accessToken);
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String nonceStr = UUID.randomUUID().toString();
        String signature = WxUtil.buildJSSDKSignature(jsapiTicket, timestamp, nonceStr, url);
        Map<String,String> map = new HashMap<String, String>();
        map.put("url",url);
        map.put("jsapi_ticket",jsapiTicket);
        map.put("nonceStr",nonceStr);
        map.put("timestamp",timestamp);
        map.put("signature",signature);
        map.put("appid",AppId);
        return map;
    }
}
