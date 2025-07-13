//员工业务数据处理
package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Employee;
import com.example.springboot.service.EmployeeService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")

public class EmployeeController {

    @Resource
    private EmployeeService employeeService;
    //get:查询操作 post:新增~ put:修改~ delete:删除~
    @PostMapping("/add")//新增数据
    public Result add(@RequestBody Employee employee){
        employeeService.add(employee);
        return Result.success();
    }
    @PutMapping("/update")//更新/修改数据
    public Result update(@RequestBody Employee employee){
        employeeService.update(employee);
        return Result.success();
    }
    @DeleteMapping("/deleteById/{id}")//删除（单个）数据
    public Result deleteById(@PathVariable Integer id){
        employeeService.deleteById(id);
        return Result.success();
    }
    @DeleteMapping("/deleteBatch")//删除（批量）数据
    public Result deleteBatch(@RequestBody List<Integer> ids){
        employeeService.deleteBatch(ids);
        return Result.success();
    }

    //查询所有数据
    @GetMapping("/selectAll")
    public Result selectAll(Employee employee) {
        List<Employee> list = employeeService.selectAll(employee);
        return Result.success(list);
    }
    //查询 by id
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Employee employee = employeeService.selectById(id);
        return Result.success(employee);
    }
    //分页查询 pageNum:当前页码 pageSize:每页个数
    @GetMapping("/selectPage")
    public Result selectPage(Employee employee,
                            @RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Employee> pageInfo = employeeService.selectPage(employee, pageNum, pageSize);
        return Result.success(pageInfo);
    }
    //通过url传参 by id
    @GetMapping("/selectOne")
    public Result selectOne(@RequestParam Integer id, @RequestParam(required = false) String no) {
        Employee employee = employeeService.selectById(id);
        return Result.success(employee);
    }
    //查询列表
    @GetMapping("/selectList")
    public Result selectList(Employee employee) {
        List<Employee> list = employeeService.selectList(employee);
        return Result.success(list);
    }

}
