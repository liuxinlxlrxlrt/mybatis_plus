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
 * XML 自定义分页,
 * mapper类中分页对象,xml中可以从里面进行取值,
 * 传递参数 Page 即自动分页,必须放在第一位(你可以继承Page实现自己的分页对象)
 */
@SpringBootTest
public class PageXmlTest {
    @Autowired
    private EmployeeService service;

    @Test
    public void testPage(){
        IPage<Employee> iPage =new Page<>(1,2);
        //调用通用业务逻辑的page业务方法
        IPage<Employee> byGender = service.getByGender(iPage, 1);
        System.out.println(byGender);
        long pages = byGender.getPages();
        System.out.println(pages);
    }
}
