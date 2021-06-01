package com.goodmap.hospital.web.sde;

import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.common.result.ResultStatus;
import com.goodmap.hospital.common.utils.RedisUtils;
import com.goodmap.hospital.pojo.second.Poi;
import com.goodmap.hospital.service.sde.poi.SdePoiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 刘智强
 * @date 2021/3/9
 * @Description
 */
@RestController
@RequestMapping("sde_poi")
@Api(tags = "地图poi数据")
public class SdePoiController {

    @Autowired
    private SdePoiService sdePoiService;
    @Autowired
    private RedisUtils redisUtils;


    @ApiOperation(value = "查询所有poi")
    @PostMapping("selectAllsde")
    public EntityResult<List<Poi>> selectAllsde() {
        boolean sdePoi = redisUtils.hasKey("sdePoi");
        if(sdePoi) {
            return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),redisUtils.get("sdePoi"));
        }else{
            List<Poi> pois = sdePoiService.selectAllsde();
            redisUtils.set("sdePoi",pois,redisUtils.getSecondsNextEarlyMorning());
            return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),pois);
        }

    }
}
