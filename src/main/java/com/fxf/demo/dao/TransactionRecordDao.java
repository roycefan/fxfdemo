package com.fxf.demo.dao;

import com.fxf.demo.dao.entities.TransactionRecordEntity;
import com.fxf.demo.service.dto.TransactionRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TransactionRecordDao {

    List<TransactionRecordEntity> list();

    int insert(@Param("entity") TransactionRecordEntity entity);

    int update(@Param("entity") TransactionRecordEntity entity);

    int delete(@Param("transactionId") long transactionId);

    //先把 tradeId 当作交易id 而不是交易员id 来处理把
    TransactionRecordEntity find(@Param("tradeId") int tradeId);

    int findMaxTradeId();
}
