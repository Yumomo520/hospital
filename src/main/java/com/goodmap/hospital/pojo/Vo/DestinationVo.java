package com.goodmap.hospital.pojo.Vo;

import com.goodmap.hospital.pojo.Poi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author 李美泉
 * @Data 2020/11/28 time
 * @Description
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DestinationVo implements Serializable {
    private String buildname;
    private List<Poi> depart;
}
