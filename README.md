# insuranceInfoCheck
保险产品准入规则测试


## 规则介绍
各保险公司针对人身、财产推出了不同的保险产品，作为商业保险公司，筛选出符合公司利益最大化的客户是非常重要的，即各保险产品的准入人群是不同的，也就是说保险公司会针对不同的人群特征，制定不同的产品缴费和赔付规则。

我们来看一下某保险产品准入规则的简化版，当不满足以下规则时，系统模块需要返回准入失败标识和失败原因
```
规则1：  保险公司是：PICC
规则2：  销售区域是：北京、天津
规则3：  投保人年龄：0 ~ 17岁
规则4：  保险期间是：20年、25年、30年
规则5：  缴费方式是：趸交（一次性交清）或年交
规则6：  保险期与交费期规则一：保险期间为20年期交费期间最长10年交且不能选择[趸交]
规则7：  保险期与交费期规则二：保险期间为25年期交费期间最长15年交且不能选择[趸交]
规则8：  保险期与交费期规则三：保险期间为30年期交费期间最长20年交且不能选择[趸交]
规则9：  被保人要求：（投保年龄+保险期间）不得大于40周岁
规则10： 保险金额规则：投保时约定，最低为5万元，超过部分必须为1000元的整数倍
规则11： 出单基本保额限额规则：线上出单基本保额限额62.5万元，超62.5万元需配合契调转线下出单
```

在本案例中规则文件是一个Excel文件，业务人员可以直接更改这个文件中指标的值，系统不需要做任何变更。


## 实现步骤
本案例还是基于Spring Boot整合Drools的架构来实现。

第一步：创建maven工程insuranceInfoCheck并配置pom.xml文件

第二步：创建/resources/application.yml文件

第三步：创建实体类InsuranceInfo

第四步：创建决策表文件 （insuranceInfoCheck.xls文件）

第五步：封装工具类KieSessionUtils

第六步：创建RuleService类

第七步：创建RuleController类

第八步：创建启动类DroolsApplication
