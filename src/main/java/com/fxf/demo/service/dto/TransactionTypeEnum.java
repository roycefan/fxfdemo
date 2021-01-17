package com.fxf.demo.service.dto;

public enum TransactionTypeEnum {

    BUY((byte) 1),
    SELL((byte) 2);

    private byte code;
    TransactionTypeEnum(byte code) {
        this.code = code;
    }

    public byte getCode() {
        return code;
    }

    public static TransactionTypeEnum getType(int dataTypeCode){
        for(TransactionTypeEnum enums:TransactionTypeEnum.values()){
            if(enums.getCode()==dataTypeCode){
                return enums;
            }
        }
        return null;
    }
}
