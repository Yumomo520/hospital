package com.goodmap.hospital.web;

import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.common.result.ResultStatus;
import com.goodmap.hospital.pojo.PeopleTrack;
import com.goodmap.hospital.pojo.Users;
import com.goodmap.hospital.service.people_track.PeopleTrackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author 刘智强
 * @date 2021/4/26
 * @Description
 */

@RestController
@Api(tags = "后台人员轨迹")
@RequestMapping("peopleTrack")
public class PeopleTrackController {

    @Autowired
    private PeopleTrackService peopleTrackService;

    @PostMapping("add")
    @ApiOperation(value = "添加")
    public EntityResult add(@RequestBody PeopleTrack peopleTrack) {

        Users users = (Users) SecurityUtils.getSubject().getPrincipal();
        peopleTrack.setUsername(users.getUsername());
        peopleTrack.setTimestamp(new Date());

        peopleTrackService.addL(peopleTrack);

        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),peopleTrack);
    }

    @GetMapping("selectAll")
    @ApiOperation(value = "查看所有")
    public EntityResult selectAll() {
        return new EntityResult(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),peopleTrackService.findAll());
    }
}
