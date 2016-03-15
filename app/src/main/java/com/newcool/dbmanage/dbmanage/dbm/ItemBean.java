package com.newcool.dbmanage.dbmanage.dbm;

/**
 * Created by niuxuan on 2016/3/10.
 */

@Table("tt_1")
public class ItemBean {

    public ItemBean(String id, String name, String tel_number, String age) {
        this.id = id;
        this.name = name;
        this.tel_number = tel_number;
        this.age = age;
    }

    public ItemBean() {
    }


    @Column("name")
    private String name;
    @Column("tel_number")
    private String tel_number;
    @Column("age")
    private String age;
    @Column("id")
    private String id;

    @Override
    public String toString() {
        return "ItemBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", tel_number='" + tel_number + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel_number() {
        return tel_number;
    }

    public void setTel_number(String tel_number) {
        this.tel_number = tel_number;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
