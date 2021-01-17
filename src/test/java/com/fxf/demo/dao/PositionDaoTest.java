package com.fxf.demo.dao;

import com.fxf.demo.dao.entities.PositionEntity;
import com.fxf.demo.dao.entities.TransactionRecordEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
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
public class PositionDaoTest {

    @Resource
    private PositionDao  positionDao;

    private List<Integer> ids = new ArrayList();

    @Test
    @Order(1)
    public void testInsert() {
        LoggerFactory.getLogger(PositionDaoTest.class);

        PositionEntity entity = new PositionEntity();
        entity.setSecurityCode("REL");
        for (int i = 0; i < 5; i++) {
            positionDao.insert(entity);
            ids.add(entity.getId());
        }
    }

    @Test
    @Order(2)
    public void testSelect() {
        List<PositionEntity> list = positionDao.list();
        assertNotNull(list);
    }

    @Test
    @Order(3)
    public void testDelete() {

        PositionEntity entity = new PositionEntity();
        for (int id : ids) {
            positionDao.delete(id);
        }

    }


}