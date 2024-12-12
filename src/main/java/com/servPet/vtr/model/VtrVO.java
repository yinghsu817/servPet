package com.servPet.vtr.model;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "VALUE_TRADING_RECORDS")
public class VtrVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VTR_ID")
    private Integer vtrId;

    @Column(name = "MEB_ID", nullable = false)
    private Integer memberId;

    @Column(name = "UPD_TIME")
    private LocalDateTime updateTime;

    @Column(name = "CRT_TIME", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "MONEY", nullable = false)
    private Integer money;

    @Column(name = "TRA_TYPE", nullable = false, length = 1)
    private String transactionType;

    // Getters and Setters
    public Integer getVtrId() {
        return vtrId;
    }

    public void setVtrId(Integer vtrId) {
        this.vtrId = vtrId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
