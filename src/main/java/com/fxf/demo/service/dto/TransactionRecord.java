package com.fxf.demo.service.dto;

import com.fxf.demo.dao.entities.TransactionRecordEntity;

public class TransactionRecord implements  BaseDTO<TransactionRecordEntity> {

    private long transactionId;
    private int tradeId;
    private int version;  // ++1  per
    private String securityCode; //
    private int quantity;
    private TransactionActionEnum action; ///Insert Update Cancel
    private TransactionTypeEnum transactionType;  // 1 buy ; 2 sell


    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
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

    public TransactionActionEnum getAction() {
        return action;
    }

    public void setAction(TransactionActionEnum action) {
        this.action = action;
    }

    public TransactionTypeEnum getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionTypeEnum transactionType) {
        this.transactionType = transactionType;
    }


    @Override
    public TransactionRecordEntity toEntity() {

        TransactionRecordEntity transactionRecordEntity=new TransactionRecordEntity();

        transactionRecordEntity.setTransactionId(this.getTransactionId());
        transactionRecordEntity.setTradeId(this.getTradeId());
        transactionRecordEntity.setTransactionType(this.getTransactionType().getCode());
        transactionRecordEntity.setSecurityCode(this.getSecurityCode());
        transactionRecordEntity.setAction(this.getAction().getCode());
        transactionRecordEntity.setQuantity(this.getQuantity());
        transactionRecordEntity.setVersion(this.getVersion());
        return transactionRecordEntity;

    }

    @Override
    public void fromEntity(TransactionRecordEntity entity) {
        this.setTransactionId(entity.getTransactionId());
        this.setTradeId(entity.getTradeId());
        this.setTransactionType(TransactionTypeEnum.getType(entity.getTransactionType())  );
        this.setSecurityCode(entity.getSecurityCode());
        this.setAction(TransactionActionEnum.getType(entity.getAction()));
        this.setQuantity(entity.getQuantity());
        this.setVersion(entity.getVersion());

    }
}
