package com.goodmap.hospital.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.lang.annotation.Target;
import java.util.List;

/**
 * @Author 李美泉
 * @Data 2020/12/22 time
 * @Description
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "emessage")
public class ErroreM {
    @Id
    private Integer id;
    private String message;
    private String poiname;
    private String textbox;
    @Transient
    private List<Message> data;
}
