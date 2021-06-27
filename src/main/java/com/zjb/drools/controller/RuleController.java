package com.zjb.drools.controller;

import com.zjb.drools.entity.InsuranceInfo;
import com.zjb.drools.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("rule")
public class RuleController {

    @Autowired
    private RuleService ruleService;
    @RequestMapping("/insuranceInfoCheck")
    public Map insuranceInfoCheck() {
        Map map = new HashMap();

        InsuranceInfo insuranceInfo = new InsuranceInfo();
        insuranceInfo.setParam1("PICC");
        insuranceInfo.setParam4("天津");
        insuranceInfo.setParam5("15");
        insuranceInfo.setParam6("12");
        insuranceInfo.setParam7("222");
        insuranceInfo.setParam8("1");
        insuranceInfo.setParam13("3");
        try {
            List<String> list = ruleService.insturanceInfoCheck(insuranceInfo);
            if (list != null && list.size() > 0) {
                map.put("checkResult", false);
                map.put("msg", "准入失败");
                map.put("detail", list);
            } else {
                map.put("checkResult", true);
                map.put("msg", "准入成功");
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("checkResult", false);
            map.put("msg", "未知错误");
            return map;
        }
    }
}
