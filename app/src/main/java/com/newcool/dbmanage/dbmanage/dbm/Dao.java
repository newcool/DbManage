package com.newcool.dbmanage.dbmanage.dbm;

/**
 * Created by 34721_000 on 2016/3/10.
 */
public interface Dao{
    public <T> void insert(T t);
    public void delete();
}
