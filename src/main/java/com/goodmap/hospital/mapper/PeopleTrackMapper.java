package com.goodmap.hospital.mapper;

import com.goodmap.hospital.pojo.PeopleTrack;
import org.apache.ibatis.annotations.Insert;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;

/**
 * @author 刘智强
 * @date 2021/4/26
 * @Description
 */
public interface PeopleTrackMapper extends Mapper<PeopleTrack>, InsertMapper<PeopleTrack>, IdListMapper<PeopleTrack,Long> {


    @Insert("insert into people_track (username,floor,x,y,longitude,latitude,timestamp) values(" +
            "#{username},#{floor},#{x},#{y},#{longitude},#{latitude},#{timestamp})")
    void addL(PeopleTrack peopleTrack);
}
