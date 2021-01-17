package com.fxf.demo.service.impl;

import com.fxf.demo.dao.PositionDao;
import com.fxf.demo.dao.TransactionRecordDao;
import com.fxf.demo.dao.entities.PositionEntity;
import com.fxf.demo.dao.entities.TransactionRecordEntity;
import com.fxf.demo.service.TransactionRecordService;
import com.fxf.demo.service.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.*;


@Service
public class TransactionRecordServiceImpl implements TransactionRecordService {

    private static final Logger LOG = LoggerFactory.getLogger(TransactionRecordServiceImpl.class);

    ExecutorService executorService = new ThreadPoolExecutor(1, 1, 2, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10000));

    @Autowired
    private PositionDao positionDao;

    @Autowired
    private TransactionRecordDao transactionRecordDao;


    public void submitTransaction(final TransactionRecord transactionRecord) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    //这里内部调用事务回滚会不生效, 线程数和执行分开就好
                    execTransaction(transactionRecord);
                } catch (Exception ex) {
                    LOG.error("execute transaction error", ex);
                }
            }

        });
    }

    @Transactional(rollbackFor = Exception.class)
    public void execTransaction(TransactionRecord transactionRecord) {
        validate(transactionRecord);
        switch (transactionRecord.getAction()) {
            case INSERT:
                insert(transactionRecord);
                break;
            case UPDATE:
                update(transactionRecord);
                break;
            case CANCEL:
                cancel(transactionRecord);
                break;
            default:
        }
    }

    public TransactionRecordList fetchAllTransactionRecord() {
        List<TransactionRecordEntity> recordEntities = transactionRecordDao.list();
        TransactionRecordList transactionRecordList = new TransactionRecordList();
        transactionRecordList.fromEntity(recordEntities);
        return transactionRecordList;
    }

    public PositionList fetchAllPosition() {
        List<PositionEntity> positionEntities = positionDao.list();
        PositionList positions = new PositionList();
        positions.fromEntity(positionEntities);
        return positions;
    }

    private void validate(TransactionRecord transactionRecord) {

    }


    /**
     * 这里有问题,先这么写
     */
    private int generateTradeId() {
        return transactionRecordDao.findMaxTradeId();
    }

    /**
     * 先这么搞了
     */
    private void insert(TransactionRecord transactionRecord) {

        transactionRecord.setVersion(1);
        transactionRecordDao.insert(transactionRecord.toEntity());
        PositionEntity positionEntity = positionDao.findBySecurityCode(transactionRecord.getSecurityCode());

        switch (transactionRecord.getTransactionType()) {
            case BUY:
                if (positionEntity != null) {
                    positionEntity.setQuantity(positionEntity.getQuantity() + transactionRecord.getQuantity());
                    positionDao.update(positionEntity);
                } else {
                    positionEntity = new PositionEntity();
                    positionEntity.setQuantity(transactionRecord.getQuantity());
                    positionEntity.setSecurityCode(transactionRecord.getSecurityCode());
                    positionDao.insert(positionEntity);
                }
                break;
            case SELL:
                if (positionEntity != null) {
                    positionEntity.setQuantity(positionEntity.getQuantity() - transactionRecord.getQuantity());
                    positionDao.update(positionEntity);
                } else {
                    positionEntity = new PositionEntity();
                    positionEntity.setQuantity(0 - transactionRecord.getQuantity());
                    positionEntity.setSecurityCode(transactionRecord.getSecurityCode());
                    positionDao.insert(positionEntity);
                }
                break;
            default:

        }
    }

    private void update(TransactionRecord transactionRecord) {

        TransactionRecordEntity oldTransactionRecordEntity = transactionRecordDao.find(transactionRecord.getTradeId());
        if (oldTransactionRecordEntity == null) {
            throw new RuntimeException("invalid operation  tradeId :" + transactionRecord.getTradeId() + "： securityCode :" + transactionRecord.getSecurityCode());
        }


        //SecurityCode 不一致 则最久的交易执行取消
        if (transactionRecord.getSecurityCode().equals(oldTransactionRecordEntity.getSecurityCode())) {

            transactionRecord.setVersion(oldTransactionRecordEntity.getVersion() + 1);
            transactionRecordDao.insert(transactionRecord.toEntity());

            //既然是更新，那就应该不存在没有的情况
            PositionEntity positionEntity = positionDao.findBySecurityCode(transactionRecord.getSecurityCode());

            switch (transactionRecord.getTransactionType()) {
                case BUY:
                    positionEntity.setQuantity(positionEntity.getQuantity() + transactionRecord.getQuantity() - oldTransactionRecordEntity.getQuantity());
                    positionDao.update(positionEntity);
                    break;
                case SELL:
                    positionEntity.setQuantity(positionEntity.getQuantity() - transactionRecord.getQuantity() + oldTransactionRecordEntity.getQuantity());
                    positionDao.update(positionEntity);
                    break;
                default:
            }

        } else {

            TransactionRecord oldTransactionRecord = new TransactionRecord();
            oldTransactionRecord.fromEntity(oldTransactionRecordEntity);

            //对老的交易执行一个cancel 操作
            cancel(oldTransactionRecord);
            //再执行一个新的insert操作交易
            transactionRecord.setAction(TransactionActionEnum.INSERT);
            transactionRecord.setTradeId(generateTradeId());
            insert(transactionRecord);


        }


    }

    private void cancel(TransactionRecord transactionRecord) {

        TransactionRecordEntity oldTransactionRecordEntity = transactionRecordDao.find(transactionRecord.getTradeId());
        transactionRecord.setVersion(oldTransactionRecordEntity.getVersion() + 1);
        transactionRecordDao.insert(transactionRecord.toEntity());

        PositionEntity positionEntity = positionDao.findBySecurityCode(transactionRecord.getSecurityCode());
        switch (TransactionTypeEnum.getType(oldTransactionRecordEntity.getTransactionType())) {
            case BUY:
                positionEntity.setQuantity(positionEntity.getQuantity() - oldTransactionRecordEntity.getQuantity());
                positionDao.update(positionEntity);
                break;
            case SELL:
                positionEntity.setQuantity(positionEntity.getQuantity() + oldTransactionRecordEntity.getQuantity());
                positionDao.update(positionEntity);
                break;
            default:

        }
    }
}
