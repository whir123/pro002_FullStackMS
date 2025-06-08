//通用接口
package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Account;
import com.example.springboot.entity.Employee;
import com.example.springboot.exception.CustomException;
import com.example.springboot.service.AdminService;
import com.example.springboot.service.EmployeeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebController {

//    private final EmployeeService employeeService;
//
//    public WebController(EmployeeService employeeService) {
//        this.employeeService = employeeService;
//    }

    @Resource
    private EmployeeService employeeService;
    @Resource
    private AdminService adminService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    //员工+管理员登录
    @PostMapping("/login")//登录接口
    public Result login(@RequestBody Account account) {
        Account result = null;
        if ("ADMIN".equals(account.getRole())) {
            result = adminService.login(account);
        } else if ("EMP".equals(account.getRole())) {
            result = employeeService.login(account);
        }  else {
            throw new CustomException ("500", "非法输入");
        }
        return Result.success(result);
    }
    @PostMapping("/register")//员工注册接口
    public Result register(@RequestBody Employee employee) {
        employeeService.register(employee);
        return Result.success();
    }
    @PutMapping("/updatePassword")//修改密码
    public Result updatePassword(@RequestBody Account account) {
        if ("ADMIN".equals(account.getRole())) {
            adminService.updatePassword(account);
        } else if ("EMP".equals(account.getRole())) {
            employeeService.updatePassword(account);
        } else {
            throw new CustomException ("500", "非法输入");
        }
        return Result.success();
    }

//    测试用：
//    @GetMapping("/weather")
//    public String weather() {
//        return "今天天气晴朗";
//    }
//    @GetMapping("/count")
//    public Result count() {
//        return Result.success(10);
//    }
//    @GetMapping("/map")
//    public Map<String, Object> map() {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("name", "LZK");
//        map.put("age", 18);
//        return map;
//    }
}
