package com.fxf.demo.service.dto;

public enum TransactionActionEnum {

    INSERT((byte) 1),
    UPDATE((byte) 2),
    CANCEL((byte) 3);

    private byte code;

    TransactionActionEnum(byte code) {
        this.code = code;
    }

    public byte getCode() {
        return code;
    }


    public static TransactionActionEnum getType(int dataTypeCode){
        for(TransactionActionEnum enums:TransactionActionEnum.values()){
            if(enums.getCode()==dataTypeCode){
                return enums;
            }
        }
        return null;
    }
}
