package com.newcool.dbmanage.dbmanage.dbm;

import java.util.List;
import java.util.Map;

/**
 * Created by 34721_000 on 2016/3/10.
 */
public interface Dao<T>{
    /**
     * 插入一条记录
     * @param t 要插入的实体
     * @param <T>
     */
    public <T> void insert(T t);

    /**
     * 根据实体的类类型查找数据库中全部数据
     * @param clazz
     * @return 返回所需要类型list
     */
    public List<T> findAll(Class<T> clazz);

    /**
     * 清除表中所有数据
     * @param clazz
     */
    public void delete(Class<T> clazz);

    /**
     * 根据条件查找
     * @param clazz 查找的类型
     * @param map 要查询字段名
     */
    public List<T> findBy(Class<T> clazz,Map<String,String> map);

    /**
     * 根据条件删除
     * @param clazz
     * @param map
     */
    public void deleteBy(Class<T> clazz,Map<String,String> map);
}
