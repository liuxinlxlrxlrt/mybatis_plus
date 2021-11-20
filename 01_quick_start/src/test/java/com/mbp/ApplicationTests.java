package com.mbp;

import com.mbp.mapper.EmployeeMapper;
import com.mbp.pojo.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ApplicationTests  extends AbstractTestNGSpringContextTests {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void add(){
        Employee employee= new Employee(null,"李嘉欣","123@qq.com","0",22,1,null,null,null);
        int insert = employeeMapper.insert(employee);
        System.out.println(insert);
    }

    @Test
    public void update(){
        Employee employee= new Employee(9,"李嘉欣","123@qq.com","0",33,1,null,null,null);
        int i = employeeMapper.updateById(employee);
        System.out.println(i);
    }

    @Test
    public void Delete(){
        employeeMapper.deleteById(4);
    }

    @Test
    public void list(){
        List<Employee> list= employeeMapper.selectByMap(null);
        for (Employee employee : list) {
            System.out.println(employee.toString());
        }
        System.out.println(list);
    }

    @Test
    public void listMapByOneCondition(){
        Map<String,Object> map = new HashMap<>();
        map.put("gender","0");
        List<Employee> list= employeeMapper.selectByMap(map);
        for (Employee employee : list) {
            System.out.println(employee.toString());
        }
        System.out.println(list);
    }

    @Test
    public void selectById(){
        Employee employee = employeeMapper.selectById(1);
        System.out.println(employee.toString());
    }

    @Test
    public void selectByAll(){
       List<Employee> list= employeeMapper.selectList();
        for (Employee employee : list) {
            System.out.println(employee.toString());
        }
    }

}
