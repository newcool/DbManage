package com.newcool.dbmanage.dbmanage.dbm;

/**
 * Created by 34721_000 on 2016/3/16.
 */
@Table("t_student")
public class Student {
    @Column("name")
    private String name;

    @ManyToOne("s_num")
    private Clazz clazz;

}
