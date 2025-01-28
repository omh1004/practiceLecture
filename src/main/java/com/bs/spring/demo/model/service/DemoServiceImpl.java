package com.bs.spring.demo.model.service;

import com.bs.spring.demo.model.dao.DemoDao;
import com.bs.spring.demo.model.dto.Demo;
import lombok.AllArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private SqlSession sqlSesson;
//    @Autowired
    private DemoDao demoDao;



    @Override
    public List<Demo> selectDemoList() {
        return demoDao.selectDemoList(sqlSesson);
    }

    @Override
    public int insertDemo(Demo demo) {
        return demoDao.insertDemo(sqlSesson, demo);
    }

    @Override
    public int updateDemo(Demo demo) {
        return 0;
    }

    @Override
    public int deleteDemo(Demo demo) {
        return 0;
    }

    @Override
    public Demo selectDemoByNo(String demoNo) {
        return null;
    }

//    @Override
//    public Demo selectDemoByNo() {
//       return null;
//    }
}
