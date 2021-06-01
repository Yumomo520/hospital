package com.goodmap.hospital.service.base;


import com.goodmap.hospital.common.result.PageResult;

import java.util.List;

public interface BaseStringService<T, TS> {
    /**
     * 返回全部列表
     *
     * @return
     */
    PageResult findAll();

    /**
     * 增加
     */
    void add(T t);

    /**
     * 添加（自动编号）
     *
     * @param t
     */
//    void addAutomatic(T t);

    /**
     * 添加时（自动编号）带返回值
     *
     * @param t
     */
//    T addAutomaticReturnObject(T t);

    /**
     * 添加时（自动编号）带返回值
     *
     * @param
     */
//    String returnIdSerialNumber();

    /**
     * 修改
     */
    void update(T t);


    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    TS findOne(String id);


    /**
     * 批量删除
     *
     * @param ids
     */
    void delete(String[] ids);

    /**
     * 删除
     *
     * @param id
     */
    int deleteByPrimaryKey(String id);

    /**
     * 分页
     *
     * @param pageNum  当前页 码
     * @param pageSize 每页记录数
     * @return
     */
    PageResult findPage(String name, int pageNum, int pageSize);

    List<String> getAllField();

}
