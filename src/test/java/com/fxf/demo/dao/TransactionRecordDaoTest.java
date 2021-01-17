package com.fxf.demo.dao;

import com.fxf.demo.dao.entities.TransactionRecordEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional // 测试数据不会写入数据库
public class TransactionRecordDaoTest {

    @Resource
    private TransactionRecordDao transactionRecordDao;

    private List<Long> ids = new ArrayList();


    @Test
    @Order(1)
    public void testInsert() {
        TransactionRecordEntity entity = new TransactionRecordEntity();
        entity.setSecurityCode("REL");
        for (int i = 0; i < 5; i++) {
            transactionRecordDao.insert(entity);
            ids.add(entity.getTransactionId());
        }
    }

    @Test
    @Order(2)
    public void testSelect() {
        List<TransactionRecordEntity> list = transactionRecordDao.list();
        assertNotNull(list);
    }

    @Test
    @Order(3)
    public void testDelete() {
        TransactionRecordEntity entity = new TransactionRecordEntity();
        for (Long id : ids) {
            transactionRecordDao.delete(id);
        }

    }


}