package com.yyh.eBank.eBank.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "EBank")
public class EBank {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //昵称
    @Column(name = "userName")
    private String userName;

    //用户名
    @Column(name = "account")
    private String account;

    //登录密码
    @Column(name = "password")
    private String password;

    //支付密码
    @Column(name = "payPassword")
    private String payPassword;

    //会话id
    @Column(name = "accessId")
    private String accessId;

    //会话id的过期时间
    @Column(name = "accessIdExp")
    private String accessIdExp;

    //账户余额
    @Column(name = "cents")
    private Integer cents;
}
