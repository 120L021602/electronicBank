package com.yyh.eBank.eBank.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Transaction")
public class Transaction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "time")
    private Long time;

    @Column(name = "balanceDiffCents")
    private Integer balanceDiffCents;

    @Column(name = "selfAccount")
    private String selfAccount;

    @Column(name = "oppositeAccount")
    private String oppositeAccount;

    @Column(name = "description")
    private String description;





}
