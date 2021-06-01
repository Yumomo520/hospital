package com.goodmap.hospital.common.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.goodmap.hospital.pojo.HisPos;
import com.goodmap.hospital.service.hispos.HisPosService;
import com.goodmap.hospital.service.wx.users.WXUserService;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;


import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author 刘智强
 * @date 2021/3/12
 * @Description
 * 
 * WebSocket服务类
 */
@ServerEndpoint(value = "/websocket/{skey}/{type}")
@Component
@Slf4j
public class WebSocketServer {

    // 这里使用静态，让 service 属于类
    private static WXUserService wxUserService;
    // 注入的时候，给类的 service 注入
    @Autowired
    public void setWxUserService(WXUserService wxUserService) {
        WebSocketServer.wxUserService = wxUserService;
    }

    private static HisPosService hisPosService;
    @Autowired
    private void setHisPosService(HisPosService hisPosService) {
        WebSocketServer.hisPosService = hisPosService;
    }


    //记录当前在线连接数
    private static int onlineCount = 0;
    //Concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象
    //private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
    private static ConcurrentHashMap<String,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    //接收skey
    private String skey = "";
    //记录导航产品使用次数
    private static int productUseNumber = 0;

    /** 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session, @PathParam("skey") String skey,@PathParam("type")String type) {
        this.session = session;
        this.skey = skey;
        skey +=   '/' + type;
        if(webSocketMap.containsKey(skey)) {
            webSocketMap.remove(skey);
            webSocketMap.put(skey,this);
        }else {
            webSocketMap.put(skey,this);
            addOnlineCount();
            addProductUseNumber();
        }

        log.info("用户连接:{},当前在线人数为:{}",skey,getOnlineCount());
        try {
            sendMessage("{\"msg\":\"连接成功\"}");
        } catch (IOException e) {
            log.error("用户:{},websocket IO 异常",skey);
        }

        OperationState(type,skey,"1");

    }

    /**
     * 连接关闭调用方法
     */
    @OnClose
    public void onClose() {
        if(webSocketMap.containsKey(skey)){
            webSocketMap.remove(skey);
            subOnlineCount();
        }
        log.info("用户 {} 退出！当前在线人数为:{}",skey,getOnlineCount());
        OperationState(skey,"0");
    }

    /**
     * 收到客户端消息后调用方法
     */
    @OnMessage
    public void onMessage(String message,Session session) {
        log.info("用户消息:{},报文:{}",skey,message);
        //群发消息
        //消息保存到数据库、redis
        if (StringUtils.isNotBlank(message)) {
            try {
                //解析发送的报文
                JSONObject jsonObject = JSON.parseObject(message);
                //追加发送人（防止串改）
                jsonObject.put("fromSkey",this.skey);
                String toskey = jsonObject.getString("toSkey");
                //传送对应toskey用户的websocket
                if(StringUtils.isNotBlank(toskey) && webSocketMap.containsKey(toskey)) {
                    webSocketMap.get(toskey).sendMessage(jsonObject.toJSONString());
                }else {
                    //否则不在这个服务器上，发送到mysql或者redis
                    log.error("请求的skey:{},不在该服务器上",toskey);
                }

                String action = jsonObject.getString("action");

                switch (action) {
                    case "locationData":
                        addHisPos(skey,message);
                        break;
                    case  "bluetoothStatus":
                        break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    @OnError
    public void onError(Session session,Throwable error) {
        log.error("用户发生错误:{},原因:{}",this.skey,error.getMessage());
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送 */
    private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     *群发自定义消息
     */
    public static void sendInfo(String message,@PathParam("skey") String skey) throws IOException{
        log.info("推送消息到:{},报文:{}",skey,message);
        if (StringUtils.isNotBlank(skey) && webSocketMap.containsKey(skey)){
            webSocketMap.get(skey).sendMessage(message);
        }else {
            log.error("用户{},不在线！",skey);
        }
    }

    //开启连接 用户状态-在线为1
    public static synchronized void OperationState(String mini,String skey,String state) {
        if(mini.equals("mini") && skey!=null){
            wxUserService.updateByskey(state,skey);
        }
    }
    //关闭连接 用户状态-下线为0
    public static synchronized void OperationState(String skey,String state) {
        if(skey!=null){
            wxUserService.updateByskey(state,skey);
        }
    }

    public static synchronized void addHisPos(String skey,String message){

        String userId = hisPosService.selectOpenidBySkey(skey);
        JSONObject json = JSONObject.parseObject(message);
        HisPos hisPos = new HisPos();
        JSONObject contentText = JSONObject.parseObject(json.getString("contentText"));

        hisPos.setOpenid(userId);
        hisPos.setFloor(contentText.getString("floor"));
        hisPos.setLongitude(contentText.getString("longitude"));
        hisPos.setLatitude(contentText.getString("latitude"));
        hisPos.setTimestamp(new Date());
        hisPosService.insertorUpdate(hisPos);

    }

    public static synchronized int getOnlineCount() {
        return onlineCount ;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer. onlineCount ++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer. onlineCount --;
    }

    public static synchronized int getProductUseCount() {
        return productUseNumber;
    }

    public static synchronized void addProductUseNumber() {
        WebSocketServer.productUseNumber ++;
    }


}
