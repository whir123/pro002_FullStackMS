//管理员业务数据处理
package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Admin;
import com.example.springboot.service.AdminService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")

public class AdminController {

    @Resource
    private AdminService adminService;
    //get:查询操作 post:新增~ put:修改~ delete:删除~
    @PostMapping("/add")//新增数据
    public Result add(@RequestBody Admin admin){
        adminService.add(admin);
        return Result.success();
    }
    @PutMapping("/update")//更新/修改数据
    public Result update(@RequestBody Admin admin){
        adminService.update(admin);
        return Result.success();
    }
    @DeleteMapping("/deleteById/{id}")//删除（单个）数据
    public Result deleteById(@PathVariable Integer id){
        adminService.deleteById(id);
        return Result.success();
    }
    @DeleteMapping("/deleteBatch")//删除（批量）数据
    public Result deleteBatch(@RequestBody List<Integer> ids){
        adminService.deleteBatch(ids);
        return Result.success();
    }

    //查询所有数据
    @GetMapping("/selectAll")
    public Result selectAll(Admin admin) {
        List<Admin> list = adminService.selectAll(admin);
        return Result.success(list);
    }
    //查询 by id
    @GetMapping("/selectById/{id}/{no}")
    public Result selectById(@PathVariable Integer id, @PathVariable String no) {
        Admin admin = adminService.selectById(id);
        return Result.success(admin);
    }
    //分页查询 pageNum:当前页码 pageSize:每页个数
    @GetMapping("/selectPage")
    public Result selectPage(Admin admin,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Admin> pageInfo = adminService.selectPage(admin, pageNum, pageSize);
        return Result.success(pageInfo);
    }
    //通过url传参 by id
    @GetMapping("/selectOne")
    public Result selectOne(@RequestParam Integer id, @RequestParam(required = false) String no) {
        Admin admin = adminService.selectById(id);
        return Result.success(admin);
    }
    //查询列表
    @GetMapping("/selectList")
    public Result selectList(Admin admin) {
        List<Admin> list = adminService.selectList(admin);
        return Result.success(list);
    }

}

