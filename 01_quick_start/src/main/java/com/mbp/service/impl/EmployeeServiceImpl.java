package com.mbp.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mbp.mapper.EmployeeMapper;
import com.mbp.pojo.Employee;
import com.mbp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 实现类需要继承通过ServiceImpl通用基类，
 * ServiceImpl<M extends BaseMapper<T>, T> implements IService<T>
 *     2个泛型 EmployeeMapper  mapper接口
 *           Employee  实体类
 *
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public IPage<Employee> getByGender(IPage page, Integer gender) {
        // 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题
        // page.setOptimizeCountSql(false);
        // 不查询总记录数
        // page.setSearchCount(false);
        // 注意！！ 分页 total 是经过插件自动 回写 到传入 page 对象
        return employeeMapper.getByGender(page,gender);
    }
}
