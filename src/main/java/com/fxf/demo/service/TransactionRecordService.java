package com.fxf.demo.service;

import com.fxf.demo.service.dto.PositionList;
import com.fxf.demo.service.dto.TransactionRecord;
import com.fxf.demo.service.dto.TransactionRecordList;
import org.springframework.transaction.annotation.Transactional;

public interface TransactionRecordService {

    void submitTransaction(final TransactionRecord transactionRecord);

     void execTransaction(TransactionRecord transactionRecord) ;

    TransactionRecordList fetchAllTransactionRecord();

    PositionList fetchAllPosition();
}
