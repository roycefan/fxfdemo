package com.fxf.demo.dao.entities;

import java.util.Date;

public class TransactionRecordEntity {

    private Long transactionId;
    private int tradeId;    //这个到底是交易员id 还是交易id ?
    private int version;  // ++1  per
    private String securityCode; //
    private int quantity;
    private byte action; ///1 Insert; 2 Update; 3 Cancel
    private byte transactionType;  // 1 buy ; 2 sell
    private Date lastModifyTime;


    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }
    public int getTradeId() {
        return tradeId;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAction() {
        return action;
    }

    public void setAction(byte action) {
        this.action = action;
    }

    public int getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(byte transactionType) {
        this.transactionType = transactionType;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        lastModifyTime = lastModifyTime;
    }

}
