package com.goodmap.hospital.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.common.result.ResultStatus;
import com.goodmap.hospital.common.utils.WxUtil;
import com.goodmap.hospital.pojo.WXUser;
import com.goodmap.hospital.service.wx.users.WXUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author 刘智强
 * @date 2021/1/26
 * @Description
 */
@RestController
@Api(tags = "微信小程序用户")
@RequestMapping("wxuser")
@Slf4j
public class WXUserController {

    @Autowired
    private WXUserService wxUserService;

    @PostMapping("login1")
    @ResponseBody
    @ApiOperation(value = "微信登录",notes = "微信登录")
    public EntityResult wxlogin(@RequestParam(value = "code",required = false) String code) {
        // 1.接收小程序发送的code
        // 2.开发者服务器 登录凭证校验接口 appId + appSecret + code
        JSONObject sessionKeyOrOpenId = WxUtil.getSessionKeyOrOpenId(code);
        // 3.接收微信接口服务，获取返回的参数
        String openid = sessionKeyOrOpenId.getString("openid");
        String sessionKey = sessionKeyOrOpenId.getString("session_key");
        log.info("openid:{},sessionkey:{}",openid,sessionKey);

        // 5.根据返回的WXUser实体类，判断是否是新用户，是的话，将用户信息存到数据库;
//        List<WXUser> wxUsers = wxUserService.selectByid(openid);
        // uuid生成唯一key，用于维护微信小程序用户与服务端的会话
        String skey = UUID.randomUUID().toString();
        WXUser wxUser = wxUserService.selectByid(openid);
        if (wxUser == null) {
            wxUser = new WXUser();
            wxUser.setOpenid(openid);
            wxUser.setSkey(skey);
            wxUser.setSessionkey(sessionKey);
            wxUserService.add(wxUser);
        } else {
            wxUser = new WXUser();
            wxUser.setOpenid(openid);
            wxUser.setSkey(skey);
            wxUser.setSessionkey(sessionKey);
            wxUserService.update(wxUser);
        }

        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),skey);
    }

    @PostMapping("login2")
    @ResponseBody
    @ApiOperation(value = "微信登录",notes = "微信登录")
    public EntityResult wxlogin(@RequestParam(value = "code",required = false) String code,
                                @RequestParam(value = "rawData",required = false) String rawData,
                                @RequestParam(value = "signature",required = false) String signature,
                                @RequestParam(value = "encrypteData",required = false) String encrypteData,
                                @RequestParam(value = "iv",required = false) String iv)
                                {
        //用户非敏感信息
        //签名:signature
        JSONObject rawDataJson = JSON.parseObject(rawData);
        // 1.接收小程序发送的code
        // 2.开发者服务器 登录凭证校验接口 appi + appsecret + code
        JSONObject sessionKeyOrOpenId = WxUtil.getSessionKeyOrOpenId(code);
        // 3.接收微信接口服务，获取返回的参数
        String openid = sessionKeyOrOpenId.getString("openid");
        String sessionKey = sessionKeyOrOpenId.getString("session_key");
        log.info("openid:{},sessionkey:{}",openid,sessionKey);
        // 4.验证签名 小程序发送的签名signature与服务器端生成的签名signature2 = sha1(rawData+sessionKey)
        String signature2 = DigestUtils.sha1Hex(rawData + sessionKey);
        if (!signature.equals(signature2)) {
            return new EntityResult("500","签名验证失败");

        }
        // 5.根据返回的WXUser实体类，判断是否是新用户，是的话，将用户信息存到数据库;
//        List<WXUser> wxUsers = wxUserService.selectByid(openid);
        // uuid生成唯一key，用于维护微信小程序用户与服务端的会话
        String skey = UUID.randomUUID().toString();
        WXUser wxUser = wxUserService.selectByid(openid);

        String nickName = rawDataJson.getString("nickName");
        String avatarUrl = rawDataJson.getString("avatarUrl");
        String gender = rawDataJson.getString("gender");
        String city = rawDataJson.getString("city");
        String country = rawDataJson.getString("country");
        String province = rawDataJson.getString("province");
        if (wxUser == null) {

            wxUser = new WXUser();
            wxUser.setOpenid(openid);
            wxUser.setSkey(skey);
            wxUser.setSessionkey(sessionKey);
            wxUser.setNickname(nickName);
            wxUser.setAvatarurl(avatarUrl);
            wxUser.setGender(gender);
            wxUser.setCity(city);
            wxUser.setCountry(country);
            wxUser.setProvince(province);

            wxUserService.add(wxUser);
        } else {
            wxUser = new WXUser();
            wxUser.setOpenid(openid);
            wxUser.setSkey(skey);
            wxUser.setSessionkey(sessionKey);
            wxUser.setNickname(nickName);
            wxUser.setAvatarurl(avatarUrl);
            wxUser.setGender(gender);
            wxUser.setCity(city);
            wxUser.setCountry(country);
            wxUser.setProvince(province);

            wxUserService.update(wxUser);
        }

        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),skey);
    }

    @GetMapping("selectAll")
    @ResponseBody
    @ApiOperation("查询全部")
    public EntityResult selectAll() {
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),wxUserService.selectAll());
    }

    @GetMapping("selectByskey")
    @ResponseBody
    @ApiOperation("验证skey存在对应的值")
    public EntityResult selectBySkey(String skey) {
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),wxUserService.selectBySkey(skey));
    }

    @PutMapping("update")
    @ResponseBody
    @ApiOperation("修改")
    public EntityResult update(String state,String skey){
        wxUserService.updateByskey(state,skey);
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage());
    }
}
