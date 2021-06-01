package com.goodmap.hospital.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table
public class Logall implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    private String username; //用户名

    private String chinaName;//姓名

    private String type; //操作类型

    private String operation; //操作

    private String method; //方法名

    private String params; //参数

    private String ip; //ip地址

    private Date createDate; //操作时间

}
