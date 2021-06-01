package com.goodmap.hospital.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import org.apache.shiro.codec.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘智强
 * @date 2021/1/18
 * @Description
 */
@Component
public class WxUtil {
    @Getter
    protected static String AppId;
    @Getter
    protected static String AppSecret;
    @Getter
    protected static String JssdkAccesstokenUrl;
    @Getter
    protected static String JssdkGetticketUrl;

    @Value("${wx.appId}")
    public void setAppId(String appId) {
        AppId = appId;
    }
    @Value("${wx.appSecret}")
    public void setAppSecret(String appSecret) {
        AppSecret = appSecret;
    }
    @Value("${wx.jssdk_accesstoken_url}")
    public void setJssdkAccesstokenUrl(String jssdkAccesstokenUrl) {
        JssdkAccesstokenUrl = jssdkAccesstokenUrl;
    }
    @Value("${wx.jssdk_getticket_url}")
    public void setJssdkGetticketUrl(String jssdkGetticketUrl) {
        JssdkGetticketUrl = jssdkGetticketUrl;
    }

    // 1.接收小程序发送的code
    // 2.开发者服务器 登录凭证校验接口 appi + appsecret + code
    public static JSONObject getSessionKeyOrOpenId(String code){
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String,String> requestUrlParam = new HashMap<>();
        //小程序66
        //AppID wxafc47f6fbe118162
        //AppSecret 843acdbe71b08a9834a98a2b66a2db58
        //小程序140
        //AppID wx1e285bb7834ce277
        //AppSecret 62e5187f3c9f2943bbcc099d49966b6f
        requestUrlParam.put("appid","wx1e285bb7834ce277");
        requestUrlParam.put("secret","62e5187f3c9f2943bbcc099d49966b6f");
        requestUrlParam.put("js_code",code);
        requestUrlParam.put("grant_type","authorization_code");
        //发送post请求读取调用微信接口获取openid用户唯一标识
        JSONObject jsonObject = JSON.parseObject(HttpClientUtils.doPost(requestUrl, requestUrlParam));
        return jsonObject;
    }

    public static JSONObject getUserInfo(String encryptedData,String sessionKey,String iv) {
        //被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        //加密密钥
        byte[] keyByte = Base64.decode(sessionKey);
        //偏移量
        byte[] ivByte = Base64.decode(iv);
        try {
            //如果密钥不足16位，那么就补足，if中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1:0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp,(byte)0);
                System.arraycopy(keyByte,0,temp,0,keyByte.length);
                keyByte = temp;
            }
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE,spec,parameters);
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSON.parseObject(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getJSSDKAccessToken() {
        String token = null;
        String url = JssdkAccesstokenUrl.replaceAll("APPID",AppId).replaceAll("APPSECRET",AppSecret);
        String json = postRequestForWeiXinService(url);
        Map map = jsonToMap(json);
        if (map != null) {
            token = (String) map.get("access_token");
        }
        return token;

    }

    /**
     *@param:  * @param null
     *@Description: 根据accessToken获取JssdkGetticket
     */
    public static String getJssdkGetticket(String accessToken) {
        String url = JssdkGetticketUrl.replaceAll("ACCESS_TOKEN", accessToken);
        String json = postRequestForWeiXinService(url);
        Map map = jsonToMap(json);
        String jsapi_ticket = null;
        if (map != null) {
            jsapi_ticket = (String) map.get("ticket");
        }
        return jsapi_ticket;
    }

    /**
     *@param:ticket 根据accessToken生成的JssdkGetticket
     *@param:timestamp 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
     *@param:nonceStr 随机字符串
     *@param:url 当前网页的URL
     *@Description: 构建分享链接的签名

     */
    public static String buildJSSDKSignature(String ticket,String timestamp,String nonceStr ,String url) throws Exception {
        String orderedString = "jsapi_ticket=" + ticket
                + "&noncestr=" + nonceStr + "&timestamp=" + timestamp
                + "&url=" + url;

        return sha1(orderedString);
    }

    /**
     * sha1 加密JSSDK微信配置参数获取签名。
     *
     * @return
     */
    public static String sha1(String orderedString) throws Exception {
        String ciphertext = null;
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] digest = md.digest(orderedString.getBytes());
        ciphertext = byteToStr(digest);
        return ciphertext.toLowerCase();
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }

    public static String mapToJson(Map map) {
        Gson gson = new Gson();
        String json = gson.toJson(map);
        return json;
    }

    private static Map jsonToMap(String json) {
        Gson gson = new Gson();
        Map map = gson.fromJson(json,new TypeToken<Map>(){}.getType());
        return map;

    }

    /**
     *
     * @param getAccessTokenUrl
     * @return
     * @Description: 调取微信接口
     */
    private static String postRequestForWeiXinService(String getAccessTokenUrl) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> postForEntity = restTemplate.postForEntity(getAccessTokenUrl, null, String.class);
        String json = postForEntity.getBody();
        return json;
    }

    private static String getDailyRetain(String beginDate,String endDate) {
        return null;
    }



}
