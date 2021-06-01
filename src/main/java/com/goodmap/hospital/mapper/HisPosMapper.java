package com.goodmap.hospital.mapper;

import com.goodmap.hospital.pojo.HisPos;
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
public interface HisPosMapper extends Mapper<HisPos>, IdListMapper<HisPos,Long>, InsertMapper<HisPos> {

    @Insert("if not exists (select 1 from his_pos where id = #{id})\n" +
            "   insert into his_pos(openid,x,y,floor,timestamp,longitude,latitude) " +
            "   values(#{openid},#{x},#{y},#{floor},#{timestamp},#{longitude},#{latitude})\n" +
            "else\n" +
            "   update his_pos set " +
            "   openid = #{openid},x=#{x},y=#{y},floor=#{floor},timestamp=#{timestamp},longitude = #{longitude},latitude = #{latitude} " +
            "   where id = #{id}")
    void insertorUpdate(HisPos hisPos);

    @Select("select w.openid from wxuser as w" +
            " where w.skey = #{skey}")
    String selectOpenidBySkey(String skey);

    @Select("select h.* from his_pos as h join wxuser as w " +
            "on w.openid = h.openid and "+
            "w.openid = (select w.openid from wxuser as w where w.skey = #{skey})")
    List<HisPos> selectByskey(String skey);
}
