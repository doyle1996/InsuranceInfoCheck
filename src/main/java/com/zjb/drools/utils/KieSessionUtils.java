package com.zjb.drools.utils;

import com.zjb.drools.entity.InsuranceInfo;
import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class KieSessionUtils {
    private KieSessionUtils() {

    }

    //把xls文件解析为String格式
    public static String getDRL(String realPath) throws FileNotFoundException {
        File file = new File(realPath);
        FileInputStream fileInputStream = new FileInputStream(file);
        SpreadsheetCompiler compiler = new SpreadsheetCompiler();
        String drl = compiler.compile(fileInputStream, InputType.XLS);
        System.out.println(drl);
        return drl;
    }
//把字符串穿件来 创建session
    public static KieSession createKiewSessionFromDRL(String drl) throws Exception {
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);
        //校验 检查语法是否正确
        Results results = kieHelper.verify();
        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)) {
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                System.out.println("Error" + message.getText());
            }
        }
        return kieHelper.build().newKieSession();
    }

    // realPath为Excel文件绝对路径
    public static KieSession getKieSessionFromXLS(String realPath) throws Exception{
        return createKiewSessionFromDRL(getDRL(realPath));
    }


}
