package com.goodmap.hospital.service.base;


import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.common.result.PageResult;

public interface BaseService<T> {



    /**
     * 返回全部列表
     *
     * @return
     */
    PageResult<T> findAll();

    /**
     * 增加
     */
    void add(T t);

    /**
     * 添加数据并返回添加成功的数据
     *
     * @param t
     * @return
     */
    EntityResult<T> addData(T t);


    /**
     * 修改
     */
    void update(T t);

    /**
     * 修改数据
     *
     * @param t
     */
    EntityResult<T> updateData(T t);

    /**
     * 删除数据
     *
     * @param id
     */
    EntityResult<Object> deleteData(Object id);


    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    T findOne(Long id);


    /**
     * 批量删除
     *
     * @param ids
     */
    void delete(Long[] ids);

    /**
     * 分页
     *
     * @param pageNum  当前页 码
     * @param pageSize 每页记录数
     * @return
     */
    PageResult<T> findPage(String name, int pageNum, int pageSize);
}
