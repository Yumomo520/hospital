package com.goodmap.hospital.web;

import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.common.result.ResultStatus;
import com.goodmap.hospital.common.utils.WxUtil;
import com.goodmap.hospital.service.wx.wxshare.WxShareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author 刘智强
 * @date 2021/1/18
 * @Description
 */
@Controller
@RequestMapping("wxshare")
@Api(tags = "微信公众号")
@Slf4j
public class WeiXinController {

    @Autowired
    private WxShareService wxShareService;

    @GetMapping("verifyWXToken")
    public void gaintoken(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");

    }


    /**
     *@param:  shareUrl 分享url地址
     *@Description: 初始化微信JSSDK Config信息
        1.先通过appId和appSecret参数请求指定微信地址 获取AccessToken
        2.在通过第一步中的AccessToken作为参数请求微信地址 获取jsapi_ticket临时票据(此处不考虑调用频率，使用者根据情况放入缓存或定时任务)
        3.通过第二步的JssdkGetticket和timestamp,nonceStr,url作为参数请求微信地址，获取签名signature
        4.将第三步获得的signature和jsapi_ticket,nonceStr,timestamp,url返回给前端，作为Config初始化验证的信息
     */
    @RequestMapping("initWXJSSDKConfigInfo")
    @ResponseBody
    @ApiOperation(value = "获取JSSDK数据",notes = "获取JSSDK数据")
    public EntityResult initWXJSConfig(@RequestParam(required = false) String url) throws Exception {
        log.info("url:{}",url);
        String json = "";
        try {
            Map map = wxShareService.initJSSDKConfig(url);
            json = WxUtil.mapToJson(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),json);
    }
}
