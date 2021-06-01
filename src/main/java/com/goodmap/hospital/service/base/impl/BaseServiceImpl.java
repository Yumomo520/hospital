package com.goodmap.hospital.service.base.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.common.result.PageResult;
import com.goodmap.hospital.common.result.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;

public class BaseServiceImpl<T> {

    private Class<T> cache = null;  //缓存子类泛型类型

    @Autowired
    Mapper<T> mapper;

    @Autowired
    IdListMapper<T, Long> idmapper;


    //查询所有
    public PageResult findAll() {
        List<T> list = mapper.selectAll();
        return new PageResult(ResultStatus.SUCCESS.getCode(), ResultStatus.SUCCESS.getMessage(), list.size(), list);
    }

    //插入
    @Transactional(rollbackFor = Exception.class)
    public void add(T t) {
        mapper.insert(t);
    }

    public EntityResult<T> addData(T t) {
        if (mapper.insert(t) == 1) {
            return new EntityResult<>(ResultStatus.SUCCESS.getCode(), ResultStatus.SUCCESS.getMessage(), t);
        }
        return new EntityResult<>(ResultStatus.ADD_ENTITY_ERROR.getCode(), ResultStatus.ADD_ENTITY_ERROR.getMessage(), null);
    }
//
    public EntityResult<T> updateData(T t) {
        if (mapper.updateByPrimaryKey(t) == 1) {
            return new EntityResult<>(ResultStatus.SUCCESS.getCode(), ResultStatus.SUCCESS.getMessage(), t);
        }
        return new EntityResult<>(ResultStatus.EDIT_ENTITY_ERROR.getCode(), ResultStatus.EDIT_ENTITY_ERROR.getMessage(), null);
    }

    public EntityResult<Object> deleteData(Object id) {
        if (mapper.deleteByPrimaryKey(id) == 1) {
            return new EntityResult<>(ResultStatus.SUCCESS.getCode(), ResultStatus.SUCCESS.getMessage(), id);
        }
        return new EntityResult<>(ResultStatus.DELETE_ENTITY_ERROR.getCode(), ResultStatus.DELETE_ENTITY_ERROR.getMessage(), null);
    }

    //更新
    @Transactional(rollbackFor = Exception.class)
    public void update(T t) {
        mapper.updateByPrimaryKeySelective(t);
    }

    //查询单个

    public T findOne(Long id) {
        return (T) mapper.selectByPrimaryKey(id);
    }

    //删除
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long[] ids) {
        idmapper.deleteByIdList(Arrays.asList(ids));
    }

    //分页查询
    public PageResult findPage(String name, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<T> page = (Page<T>) mapper.selectAll();
        return new PageResult(ResultStatus.SUCCESS.getCode(), ResultStatus.SUCCESS.getMessage(), page.getTotal(), page.getResult());
    }

    /**
     * 查询一条数据
     *
     * @param t
     * @return
     */
//    public T queryOne(T t) {
//        return mapper.selectOne(t);
//    }

    /**
     * 通用查询指定字段service
     *
     * @param where        查询条件
     * @param orderByField 排序字段
     * @param fields       实体类名
     * @return
     */
//    public T queryOneByFiled(Sqls where, String orderByField, String... fields) {
//        return (T) queryByFiledBase(null, null, where, orderByField, fields).get(0);
//    }


    /**
     * 通过查询字段获取集合
     *
     * @param where        where查询条件
     * @param orderByField 排序字段
     * @param fields       检索字段
     * @return List<T>
     */
//    public List<T> queryListByFiled(Sqls where, String orderByField, String... fields) {
//        return queryByFiledBase(null, null, where, orderByField, fields);
//    }

    /**
     * 通过字段查询分页
     *
     * @param pageNo       起始页
     * @param pageSize     页大小
     * @param where        查询条件
     * @param orderByField 排序字段
     * @param fields       查询字段
     * @return pageInfo 分页对象
     */
//    public PageInfo<T> queryListByPageAadFiled(Integer pageNo, Integer pageSize, Sqls where, String orderByField, String... fields) {
//        return new PageInfo<T>(queryByFiledBase(pageNo, pageSize, where, orderByField, fields));
//    }

    /**
     * 通过字段查询依托通用方法
     *
     * @param pageNo       起始页
     * @param pageSize
     * @param where
     * @param orderByField
     * @param fields
     * @return
     */

    private List<T> queryByFiledBase(Integer pageNo, Integer pageSize, Sqls where, String orderByField, String... fields) {
        Example.Builder builder = null;
        if (null == fields || fields.length == 0) {
            //查询所有
            builder = Example.builder(getTypeArguement());

        } else {
            //查询指定字段
            builder = Example.builder(getTypeArguement())
                    .select(fields);
        }
        if (where != null) {
            builder = builder.where(where);
        }

        if (orderByField != null) {
            builder = builder
                    .orderByDesc(orderByField);
        }
        Example example = builder.build();

        if (pageNo != null && pageSize != null) {
            PageHelper.startPage(pageNo, pageSize);    //分页插件
        }

        List<T> list = getMapper().selectByExample(example);
        return list;
    }

    /**
     * 查询多条结果
     *
     * @param t 查询条件
     * @return
     */
//    public List<T> queryList(T t) {
//        return mapper.select(t);
//    }

    /**
     * 分页查询
     *
     * @param t        条件
     * @param pageNo   当前页
     * @param pageSize 页大小
     * @return
     */
//    public PageInfo<T> queryListByPage(T t, int pageNo, int pageSize) {
//        PageHelper.startPage(pageNo, pageSize);
//        List<T> select = mapper.select(t);
//        PageInfo<T> pageInfo = new PageInfo<>(select);
//        return pageInfo;
//    }

    /**
     * 获取子类泛型类型
     *
     * @return
     */

    public Class<T> getTypeArguement() {
        if (cache == null)
            cache = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return cache;
    }



    protected Mapper getMapper() {
        return mapper;
    }


//    protected IdListMapper geetidListMapper() {
//        return idmapper;
//    }

    /**
     * 获取所有字段
     *
     * @return
     */
//    public List<String> getAllField() {
//        Field[] fields = getTypeArguement().getDeclaredFields();
//        List<String> list = new ArrayList<>();
//        for (Field field : fields) {
//            field.setAccessible(true);
//            Class<?> type = field.getType();
//            //获取属性
//            String name = field.getName();
//            list.add(type.getSimpleName() + ":" + name);
//        }
//        return list;
//    }
}
