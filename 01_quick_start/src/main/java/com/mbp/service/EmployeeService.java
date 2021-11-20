package com.mbp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mbp.pojo.Employee;

public interface EmployeeService extends IService<Employee> {

    IPage<Employee> getByGender(IPage page, Integer gender);
}
