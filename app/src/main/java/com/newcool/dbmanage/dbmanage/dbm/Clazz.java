package com.newcool.dbmanage.dbmanage.dbm;

import java.util.List;

/**
 * Created by 34721_000 on 2016/3/16.
 */
@Table("t_class")
public class Clazz {


    @Column("s_num")
    private String s_num;

    @Column("name")
    private String name;

    @Column("tel_namber_n")
    private String tel_number_n;

    @OneToMany("t_class")
    private List<Student> list;

    public String getS_num() {
        return s_num;
    }

    public void setS_num(String s_num) {
        this.s_num = s_num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel_number_n() {
        return tel_number_n;
    }

    public void setTel_number_n(String tel_number_n) {
        this.tel_number_n = tel_number_n;
    }

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }
}
