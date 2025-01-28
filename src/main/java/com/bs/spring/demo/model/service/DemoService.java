package com.bs.spring.demo.model.service;

import com.bs.spring.demo.model.dto.Demo;

import java.util.List;

public interface DemoService {
    List<Demo> selectDemoList();
    int insertDemo(Demo demo);
    int updateDemo(Demo demo);
    int deleteDemo(Demo demo);
    Demo selectDemoByNo(String demoNo);

}
