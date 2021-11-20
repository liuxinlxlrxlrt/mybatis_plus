package com.mbp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbp.pojo.Employee;
import com.mbp.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * MP-分页查询:需先配置配置插件,MybatisPlusConfig类中配置
 */
@SpringBootTest
public class PageTest {

    @Autowired
    private EmployeeService service;

    @Test
    public void testPage(){
        IPage<Employee> iPage =new Page<>(2,2);
        //调用通用业务逻辑的page业务方法
        IPage<Employee> page = service.getByGender(iPage,0);
        List<Employee> records = page.getRecords();
        records.forEach(System.out::println);
        long pages = page.getPages();
        System.out.println(pages);
    }
}
