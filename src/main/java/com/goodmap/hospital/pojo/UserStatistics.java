package com.goodmap.hospital.pojo;

import com.goodmap.hospital.pojo.Vo.StatsData;
import lombok.Data;

import javax.persistence.Table;
import java.util.List;

/**
 * @author 刘智强
 * @date 2021/3/20
 * @Description
 */
@Data
@Table(name = "user_statistics")
public class UserStatistics {

    private Integer id;
    private String popularDepartmentSearch;//热门科室搜索
    private String productUseNumber;//产品使用次数
    private String routePlanNumber;//路线规划次数
    private String user_number;

    private List<StatsData> data;


}
