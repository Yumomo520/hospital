package com.goodmap.hospital.web;

import com.goodmap.hospital.common.websocket.WebSocketServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * @author 刘智强
 * @date 2021/3/12
 * @Description
 */

@RestController
@RequestMapping ( "/websocket" )
public class WebSocketController {


    @GetMapping("index")
    public ResponseEntity<String> index(){
        return ResponseEntity.ok("请求成功");
    }

    @GetMapping("page")
    public ModelAndView page(){
        return new ModelAndView("websocket");
    }

    @RequestMapping("/push/{toUserId}")
    public ResponseEntity<String> pushToWeb(String message,@PathVariable String toUserId) throws IOException{
        WebSocketServer.sendInfo(message,toUserId);
        return ResponseEntity.ok("MESSAGE发送成功");
    }

//    // 页面请求
//    @GetMapping ( "/socket/{cid}" )
//    public ModelAndView socket( @PathVariable String cid) {
//        ModelAndView mav= new ModelAndView( "/socket" );
//        mav.addObject( "cid" , cid);
//        return mav;
//    }
//    // 推送数据接口
//    @ResponseBody
//    @RequestMapping ( "/socket/push/{cid}" )
//    public String pushToWeb( @PathVariable String cid,String message) {
//        try {
//            WebSocketServer. sendInfo (message,cid);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return cid+ "#" +e.getMessage();
//        }
//        return cid;
//    }
}
