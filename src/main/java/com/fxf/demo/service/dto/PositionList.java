package com.fxf.demo.service.dto;

import com.fxf.demo.dao.entities.PositionEntity;

import java.util.ArrayList;
import java.util.List;

public class PositionList extends ArrayList<Position> implements BaseDTO<List<PositionEntity>> {


    @Override
    public List<PositionEntity> toEntity() {
        List<PositionEntity> positionEntityList = new ArrayList<>();
        for (Position position : this) {
            positionEntityList.add(position.toEntity());

        }
        return positionEntityList;
    }

    @Override
    public void fromEntity(List<PositionEntity> entitys) {

        for (PositionEntity positionEntity : entitys) {
            Position position = new Position();
            position.fromEntity(positionEntity);
            this.add(position);
        }
    }
}
