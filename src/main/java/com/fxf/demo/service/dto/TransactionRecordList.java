package com.fxf.demo.service.dto;

import com.fxf.demo.dao.entities.TransactionRecordEntity;

import java.util.ArrayList;
import java.util.List;

public class TransactionRecordList  extends ArrayList<TransactionRecord>  implements BaseDTO<List<TransactionRecordEntity>>  {
    @Override
    public List<TransactionRecordEntity> toEntity() {
        List<TransactionRecordEntity>  transactionRecordEntityList = new ArrayList<>();
        for (TransactionRecord  record : this) {
            transactionRecordEntityList.add(record.toEntity());
        }
        return transactionRecordEntityList;
    }

    @Override
    public void fromEntity(List<TransactionRecordEntity> entitys) {
        for (TransactionRecordEntity transactionRecordEntity : entitys) {
            TransactionRecord  transactionRecord=new TransactionRecord();
            transactionRecord.fromEntity(transactionRecordEntity);
            this.add(transactionRecord);
        }
    }
}
