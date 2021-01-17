package com.fxf.demo.service.dto;

import com.fxf.demo.dao.entities.PositionEntity;

public class Position implements BaseDTO<PositionEntity> {

    private String securityCode;
    private int quantity;


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

    @Override
    public PositionEntity toEntity() {
        PositionEntity recordEntity = new PositionEntity();
        recordEntity.setQuantity(this.getQuantity());
        recordEntity.setSecurityCode(this.getSecurityCode());
        return recordEntity;
    }

    @Override
    public void fromEntity(PositionEntity entity) {
        this.setQuantity(entity.getQuantity());
        this.setSecurityCode(entity.getSecurityCode());
    }
}


