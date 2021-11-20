package com.mbp;


import com.mbp.pojo.Employee;
import com.mbp.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.List;


@SpringBootTest
class ServiceTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private EmployeeService service;

    @Test
    public void add(){
        Employee employee= new Employee(null,"高圆圆","123@qq.com","0",32,1,null,null,null);
        boolean b = service.saveOrUpdate(employee);
        System.out.println(b);
    }

    @Test
    public void update(){

    }

    @Test
    public void logicDelete(){
        boolean byId = service.removeById(4);
    }

    @Test
    public void list(){
        List<Employee> list = service.list();
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    @Test
    public void selectById(){
        Employee byId = service.getById(5);
        System.out.println(byId);
    }

    @Test
    public void testCAS(){
        //线程1：age 18 version 1
        Employee employee1 = service.getById(1);

        //线程2 age 18 version 1
        Employee employee2 = service.getById(1);
        employee1.setAge(50);
        employee2.setAge(100);

        //update 50 version 1 where verison1=数据库version1
        boolean b1 = service.updateById(employee1);
        System.out.println(b1);
        if (b1==true){
            System.out.println("更新成功");
        }
        System.out.println("-------------------");
        //update 100 version 2  where verison2=数据库version2
        boolean b2 = service.updateById(employee2);
        System.out.println(b2);
        if (b2==false){
            System.out.println("更新失败，请重新刷新页面并更新");
        }
    }

}
