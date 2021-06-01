package com.goodmap.hospital.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 刘智强
 * @date 2021/1/26
 * @Description
 */
@Data
@Table(name = "wxuser")
public class WXUser {

    @Id
    private String openid;
    private String skey;
    private String sessionkey;
    private String nickname;
    private String avatarurl;
    private String gender;
    private String city;
    private String country;
    private String province;
    private String state;
}
