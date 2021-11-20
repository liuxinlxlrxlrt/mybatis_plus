package com.mbp;

import com.mbp.pojo.Employee;
import com.mbp.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * MP通用Service-CRUD
 */
@SpringBootTest
public class QuickStartApplicationTest {

    @Autowired
    private EmployeeService service;

    @Test
    public void query(){
        Employee employee = service.getById(1);
        System.out.println(employee);
    }

    //saveOrUpdate：根据主键，查到有就更新，没有查到就插入
    @Test
    public void saveOrUpdate(){
        Employee employee= new Employee(6,"董书含","dongshuhan@qq.com","0",21,1,null,null,null);
        boolean b = service.saveOrUpdate(employee);
        System.out.println(b);
    }

//    @Test
//    public void removeByIds(){
//        List<Integer> integerList = Arrays.asList(1, 3);
//        boolean b = service.removeByIds(integerList);
//        System.out.println(b);
//    }

    @Test
    public void listByIds() {
        List<Integer> integerList = Arrays.asList(1,2,3);
        List<Employee> listByIds = service.listByIds(integerList);
        System.out.println(listByIds);
    }



}
