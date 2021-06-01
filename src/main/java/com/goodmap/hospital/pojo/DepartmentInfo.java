package com.goodmap.hospital.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 刘智强
 * @date 2021/5/12
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "department_information")
public class DepartmentInfo {

    @Id
    private Integer id;
    private String name;
    private String introduction;
}
