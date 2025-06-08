package com.example.springboot.service;

import cn.hutool.core.util.StrUtil;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Account;
import com.example.springboot.entity.Admin;
import com.example.springboot.entity.Employee;
import com.example.springboot.exception.CustomException;
import com.example.springboot.mapper.EmployeeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

//创建service 并且标注为spring里面的一个bean
@Service
public class EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;
    //void 表示方法没有返回值，但方法内部仍可以执行业务逻辑（操作数据库等）
    public void add(Employee employee) { //增
        String username = employee.getUsername();
        Employee dbEmployee = employeeMapper.selectByUsername(username);
        if (dbEmployee != null) {//账号已存在
            throw new CustomException("500", "账号已存在，请更换别的账号");
        }
        if (StrUtil.isBlank(employee.getPassword())) { //没填密码 默认密码
            employee.setPassword("123456");
        }
        if (StrUtil.isBlank(employee.getName())) { //没填名字 默认账号名字
            employee.setName(employee.getUsername());
        }
        //一定要设置角色
        employee.setRole("EMP");
        employeeMapper.insert(employee);
    }
    public void update(Employee employee) { //改
        employeeMapper.updateById(employee);
    }
    public void deleteById(Integer id) { //删单
        employeeMapper.deleteById(id);
    }
    public void deleteBatch(List<Integer> ids) { //删多
        for (Integer id : ids) {
            this.deleteById(id);
        }
    }

    public List<Employee> selectAll(Employee employee) {
        // 可以写额外的业务操作
        return employeeMapper.selectAll(employee);
    }
    public Employee selectById(Integer id) {
        return employeeMapper.selectById(id);
    }
    //实现分页查询
    public PageInfo<Employee> selectPage(Employee employee,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Employee> list = employeeMapper.selectAll(employee);
        return PageInfo.of(list);
    }
    public List<Employee> selectList(Employee employee) {
        System.out.println(employee);
        return null;
    }

    public Employee login(Account account) {
        String username = account.getUsername();
        Employee dbEmployee = employeeMapper.selectByUsername(username);
        if (dbEmployee == null) {// 没查询到的话
            throw new CustomException("500","账号不存在");
        }
        //数据库存在帐号了 再去判断密码
        String password = account.getPassword();
        if (!dbEmployee.getPassword().equals(password)) {
            throw new CustomException("500","账号或密码错误");
        }
        return dbEmployee;
    }
    //注册直接调用新增的逻辑
    public void register(Employee employee) {
        this.add(employee);
    }

    public void updatePassword(Account account) {
        Integer id = account.getId();
        Employee employee = this.selectById(id);
        if (!employee.getPassword().equals(account.getPassword())) {//如果原密码不匹配
            throw new CustomException("500","对不起，原密码错误");
        }
        employee.setPassword(account.getNewPassword());//设置新密码
        this.update(employee);
    }
}
