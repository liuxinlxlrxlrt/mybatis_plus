package com.mbp;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mbp.mapper.EmployeeMapper;
import com.mbp.pojo.Employee;
import com.mbp.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 条件构造器
 */
@SpringBootTest
public class WrapperTest {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testQuery1Wrapper(){
        QueryWrapper<Employee> queryWrapper = new QueryWrapper();
        queryWrapper
                .select("last_name","age")
                .eq("last_name","董书含");

        List<Employee> list = employeeService.list(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void testQuery2Wrapper(){
        QueryWrapper<Employee> queryWrapper = new QueryWrapper();
        queryWrapper
                .between("age",21,24)
                .or()
                .inSql("id","select 1 from dual");
        List<Employee> list = employeeService.list(queryWrapper);
        list.forEach(System.out::println);
    }


    @Test
    public void testUpdate1Wrapper(){
        UpdateWrapper<Employee> updateWrapper = new UpdateWrapper<>();
        updateWrapper
                .set("last_name","伍珂月")
                .eq("id",3)
                .setSql("age='22'");

        employeeService.update(updateWrapper);
    }

    //链式调用 lambda 表达式
    @Test
    public void testLambdaQuery1Wrapper(){
        QueryWrapper<Employee> queryWrapper = new QueryWrapper();
         queryWrapper.lambda()
                 .select(Employee::getLastName,Employee::getAge)
                 .eq(Employee::getLastName,"单依纯");


        List<Employee> list = employeeService.list(queryWrapper);
        list.forEach(System.out::println);
    }
}
