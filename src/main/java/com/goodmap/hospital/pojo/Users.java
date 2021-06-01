package com.goodmap.hospital.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author 李美泉
 * @Data 2020/9/16 time
 * @Description
 **/
@Data
@ApiModel("用户")
@Table
//用户信息保存到HttppSession对象，需要实现序列化接口
public class Users implements Serializable {
    private static final long serialVersionUID = 726928464L;
    @Id
    @KeySql(useGeneratedKeys = true)
    private int id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("姓名")
    private String chineseName;
    @ApiModelProperty("性别")
    private String sex;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("电话")
    private Integer telephone;
    @ApiModelProperty("状态")
    private int state;
    @ApiModelProperty("角色id")
    private int roleId;
    @ApiModelProperty("备注")
    private String remark;
}
