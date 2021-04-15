package com.ib.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class TransactionEwallet {
    @EmbeddedId
    private TransactionEwalletId id;

    @Column
    private String account_number;

    @Column
    private String full_name;

    @Column
    private int id_card;

    @Column
    private Date issue_date;

    @Column
    private Date expired_date;

    @Column
    private String idcard_type;

    public TransactionEwalletId getId() {
        return id;
    }

    public void setId(TransactionEwalletId id) {
        this.id = id;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getId_card() {
        return id_card;
    }

    public void setId_card(int id_card) {
        this.id_card = id_card;
    }

    public Date getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(Date issue_date) {
        this.issue_date = issue_date;
    }

    public Date getExpired_date() {
        return expired_date;
    }

    public void setExpired_date(Date expired_date) {
        this.expired_date = expired_date;
    }

    public String getIdcard_type() {
        return idcard_type;
    }

    public void setIdcard_type(String idcard_type) {
        this.idcard_type = idcard_type;
    }
}
