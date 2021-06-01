package com.goodmap.hospital.mapper;

import com.goodmap.hospital.pojo.WXUser;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;

import java.util.List;

/**
 * @author 刘智强
 * @date 2021/1/26
 * @Description
 */
public interface WXUserMapper extends Mapper<WXUser>, IdListMapper<WXUser,Long>, InsertMapper<WXUser> {

    @Select("select * from wxuser where skey = #{skey}")
    List<WXUser> selectBySkey(String skey);

    @Update("update wxuser set state = #{state} where skey = #{skey}")
    void updateBySkey(String state,String skey);
}
