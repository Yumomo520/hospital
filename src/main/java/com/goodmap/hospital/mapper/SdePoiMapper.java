package com.goodmap.hospital.mapper;

import com.goodmap.hospital.pojo.second.Poi;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;

import java.util.List;

/**
 * @author 刘智强
 * @date 2021/3/9
 * @Description
 */
public interface SdePoiMapper extends Mapper<Poi>, IdListMapper<Poi,Long>, InsertMapper<Poi> {

    @Select("select OBJECTID,unitname,buildname,poiname,floor,longitude,latitude,x,y,fnum,buildid,remark,picurl,videourl,Shape from 一楼poi\n" +
            "union all select OBJECTID,unitname,buildname,poiname,floor,longitude,latitude,x,y,fnum,buildid,remark,picurl,videourl,Shape from 二楼poi\n" +
            "union all select OBJECTID,unitname,buildname,poiname,floor,longitude,latitude,x,y,fnum,buildid,remark,picurl,videourl,Shape from 三楼poi\n" +
            "union all select OBJECTID,unitname,buildname,poiname,floor,longitude,latitude,x,y,fnum,buildid,remark,picurl,videourl,Shape from 四楼poi\n" +
            "union all select OBJECTID,unitname,buildname,poiname,floor,longitude,latitude,x,y,fnum,buildid,remark,picurl,videourl,Shape from 五楼poi\n" +
            "union all select OBJECTID,unitname,buildname,poiname,floor,longitude,latitude,x,y,fnum,buildid,remark,picurl,videourl,Shape from 六楼poi\n" +
            "union all select OBJECTID,unitname,buildname,poiname,floor,longitude,latitude,x,y,fnum,buildid,remark,picurl,videourl,Shape from 七楼poi\n" +
            "union all select OBJECTID,unitname,buildname,poiname,floor,longitude,latitude,x,y,fnum,buildid,remark,picurl,videourl,Shape from 八楼poi\n" +
            "union all select OBJECTID,unitname,buildname,poiname,floor,longitude,latitude,x,y,fnum,buildid,remark,picurl,videourl,Shape from 九楼poi\n" +
            "union all select OBJECTID,unitname,buildname,poiname,floor,longitude,latitude,x,y,fnum,buildid,remark,picurl,videourl,Shape from 十楼poi\n" +
            "union all select OBJECTID,unitname,buildname,poiname,floor,longitude,latitude,x,y,fnum,buildid,remark,picurl,videourl,Shape from 十一楼poi\n" +
            "union all select OBJECTID,unitname,buildname,poiname,floor,longitude,latitude,x,y,fnum,buildid,remark,picurl,videourl,Shape from 十二楼poi\n" +
            "union all select OBJECTID,unitname,buildname,poiname,floor,longitude,latitude,x,y,fnum,buildid,remark,picurl,videourl,Shape from 十三楼poi\n" +
            "union all select OBJECTID,unitname,buildname,poiname,floor,longitude,latitude,x,y,fnum,buildid,remark,picurl,videourl,Shape from 十四楼poi\n"
            )
    List<Poi> selectAllsde();
}
