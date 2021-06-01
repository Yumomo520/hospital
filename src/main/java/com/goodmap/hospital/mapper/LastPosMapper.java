package com.goodmap.hospital.mapper;

import com.goodmap.hospital.pojo.LastPos;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;


import java.util.List;

/**
 * @author 刘智强
 * @date 2021/1/25
 * @Description
 */
public interface LastPosMapper extends Mapper<LastPos>, IdListMapper<LastPos,Long>, InsertMapper<LastPos> {

    @Insert("if not exists (select 1 from last_pos where openid = #{openid})\n" +
            "   insert into last_pos(openid,x,y,floor,timestamp) " +
            "   values(#{openid},#{x},#{y},#{floor},#{timestamp})\n" +
            "else\n" +
            "   update last_pos set " +
            "   x=#{x},y=#{y},floor=#{floor},timestamp=#{timestamp} " +
            "   where openid = #{openid}")
    void insertorUpdate(LastPos lastPos);

    @Select("select w.openid from wxuser as w" +
            " where w.skey = #{skey}")
    String selectOpenidBySkey(String skey);

    @Select("select l.* from last_pos as l join wxuser as w " +
            "on w.openid = l.openid and "+
            "w.openid = (select w.openid from wxuser as w where w.skey = #{skey})")
    List<LastPos> selectByskey(String skey);
}
