package com.newcool.dbmanage.dbmanage.dbm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 34721_000 on 2016/3/10.
 */
@Retention(RetentionPolicy.RUNTIME)
 @Target(ElementType.TYPE)
 public @interface Table {
    public String value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column{
    public String value();
}
