package com.bs.spring.demo.model.dao;

import com.bs.spring.demo.model.dto.Demo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public interface DemoDao {
    List<Demo> selectDemoList(SqlSession session);
    int insertDemo(SqlSession session, Demo demo);
    int updateDemo(SqlSession session, Demo demo);
    int deleteDemo(SqlSession session, Demo demo);
    Demo selectDemoByNo(SqlSession session, Long id);
}
