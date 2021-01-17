package com.fxf.demo.dao;

import com.fxf.demo.dao.entities.PositionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PositionDao {

    List<PositionEntity> list();

    int insert(@Param("entity") PositionEntity entity);

    int update(@Param("entity") PositionEntity entity);

    int delete(@Param("id") int id);

    PositionEntity findBySecurityCode( @Param("securityCode") String securityCode  );

}
