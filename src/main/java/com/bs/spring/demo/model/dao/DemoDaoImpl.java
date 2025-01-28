package com.bs.spring.demo.model.dao;

import com.bs.spring.demo.model.dto.Demo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DemoDaoImpl  implements DemoDao {
    @Override
    public List<Demo> selectDemoList(SqlSession session) {
        return session.selectList("demo.selectDemoList");
    }

    @Override
    public int insertDemo(SqlSession session, Demo demo) {
        return  session.insert("demo.insertdemo", demo);
    }

    @Override
    public int updateDemo(SqlSession session, Demo demo) {
        return 0;
    }

    @Override
    public int deleteDemo(SqlSession session, Demo demo) {
        return 0;
    }

    @Override
    public Demo selectDemoByNo(SqlSession session, Long id) {
        return null;
    }

//    @Override
//    public Demo selectDemoByNo(SqlSession session, Long id) {
//        return null;
//    }
}
