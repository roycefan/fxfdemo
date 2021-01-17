package com.fxf.demo.service;

import com.fxf.demo.dao.PositionDao;
import com.fxf.demo.dao.TransactionRecordDao;
import com.fxf.demo.dao.entities.PositionEntity;
import com.fxf.demo.dao.entities.TransactionRecordEntity;
import com.fxf.demo.service.dto.*;
import com.fxf.demo.service.impl.TransactionRecordServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional // 测试数据不会写入数据库
public class TransactionRecordServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(TransactionRecordServiceTest.class);


    @Resource
    private PositionDao positionDao;

    @Resource
    private TransactionRecordDao transactionRecordDao;

    @Resource
    private TransactionRecordService transactionRecordService;


    @Before
    public void setUp() throws Exception {
        List<PositionEntity> positionEntityList = positionDao.list();
        for (PositionEntity positionEntity : positionEntityList) {
            positionDao.delete(positionEntity.getId());
        }
        List<TransactionRecordEntity> transactionRecordEntities = transactionRecordDao.list();
        for (TransactionRecordEntity transactionRecordEntity : transactionRecordEntities) {
            transactionRecordDao.delete(transactionRecordEntity.getTransactionId());
        }


    }

    private List<TransactionRecord> generateTestData() {
        List<TransactionRecord> transactionRecordList = new ArrayList<>();

        //1
        TransactionRecord transactionRecord = new TransactionRecord();
        transactionRecord.setTradeId(1);
        transactionRecord.setSecurityCode("REL");
        transactionRecord.setQuantity(50);
        transactionRecord.setAction(TransactionActionEnum.INSERT);
        transactionRecord.setTransactionType(TransactionTypeEnum.BUY);
        transactionRecordList.add(transactionRecord);


        //2
        transactionRecord = new TransactionRecord();
        transactionRecord.setTradeId(2);
        transactionRecord.setSecurityCode("ITC");
        transactionRecord.setQuantity(40);
        transactionRecord.setAction(TransactionActionEnum.INSERT);
        transactionRecord.setTransactionType(TransactionTypeEnum.SELL);
        transactionRecordList.add(transactionRecord);


        //3
        transactionRecord = new TransactionRecord();
        transactionRecord.setTradeId(3);
        transactionRecord.setSecurityCode("INF");
        transactionRecord.setQuantity(70);
        transactionRecord.setAction(TransactionActionEnum.INSERT);
        transactionRecord.setTransactionType(TransactionTypeEnum.BUY);
        transactionRecordList.add(transactionRecord);

        //4
        transactionRecord = new TransactionRecord();
        transactionRecord.setTradeId(1);
        transactionRecord.setSecurityCode("REL");
        transactionRecord.setQuantity(60);
        transactionRecord.setAction(TransactionActionEnum.UPDATE);
        transactionRecord.setTransactionType(TransactionTypeEnum.BUY);
        transactionRecordList.add(transactionRecord);

        //5
        transactionRecord = new TransactionRecord();
        transactionRecord.setTradeId(2);
        transactionRecord.setSecurityCode("ITC");
        transactionRecord.setQuantity(30);
        transactionRecord.setAction(TransactionActionEnum.CANCEL);
        transactionRecord.setTransactionType(TransactionTypeEnum.BUY);
        transactionRecordList.add(transactionRecord);

        //6
        transactionRecord = new TransactionRecord();
        transactionRecord.setTradeId(4);
        transactionRecord.setSecurityCode("INF");
        transactionRecord.setQuantity(20);
        transactionRecord.setAction(TransactionActionEnum.INSERT);
        transactionRecord.setTransactionType(TransactionTypeEnum.SELL);
        transactionRecordList.add(transactionRecord);

        return transactionRecordList;

    }

    @Test
    public void submitTransaction() {

        List<TransactionRecord> testRecords = generateTestData();
        for (TransactionRecord testRecord : testRecords) {
            transactionRecordService.submitTransaction(testRecord);
        }

        //前面是做异步处理，所以要等一下才能看到正确结果
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        PositionList positions = transactionRecordService.fetchAllPosition();

        for (Position position : positions) {
            if (position.getSecurityCode().equals("REL")) {
                LOG.info("REL : " + position.getQuantity());
                assertEquals(position.getQuantity(), 60);
            }

            if (position.getSecurityCode().equals("ITC")) {
                LOG.info("ITC : " + position.getQuantity());
                assertEquals(position.getQuantity(), 0);
            }

            if (position.getSecurityCode().equals("INF")) {
                LOG.info("INF : " + position.getQuantity());
                assertEquals(position.getQuantity(), 50);
            }
        }


    }
}