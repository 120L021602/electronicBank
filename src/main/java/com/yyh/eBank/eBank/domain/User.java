package com.yyh.eBank.eBank.domain;

import jdk.jfr.DataAmount;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity//实体类
@Table(name = "user")//对应哪张表
public class User {
    @Id//这是主键
    @Column(name = "id")//数据库中的id对应属性中的id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

}
