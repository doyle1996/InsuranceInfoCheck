package com.zjb.drools.service;

import com.zjb.drools.entity.InsuranceInfo;
import com.zjb.drools.utils.KieSessionUtils;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RuleService {
    public List<String> insturanceInfoCheck(InsuranceInfo insuranceInfo) throws Exception{
        KieSession session = KieSessionUtils.getKieSessionFromXLS("src/main/resources/rules/insuranceInfoCheck.xls");
        session.getAgenda().getAgendaGroup("sign").setFocus(); //设置组 获取焦点
        session.insert(insuranceInfo); //加入实体类
        ArrayList<String> listRules = new ArrayList<>(); //设置全局变量
        session.setGlobal("listRules",listRules);
        session.fireAllRules();
        session.dispose();
        return listRules; //返回全局变量
    }


}
