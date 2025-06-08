package com.example.springboot.service;

import cn.hutool.core.util.StrUtil;
import com.example.springboot.entity.Account;
import com.example.springboot.entity.Admin;
import com.example.springboot.exception.CustomException;
import com.example.springboot.mapper.AdminMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

//创建service 并且标注为spring里面的一个bean
@Service
public class AdminService {

    @Resource
    private AdminMapper adminMapper;
    //void 表示方法没有返回值，但方法内部仍可以执行业务逻辑（操作数据库等）
    public void add(Admin admin) { //增
        String username = admin.getUsername();
        Admin dbAdmin = adminMapper.selectByUsername(username);
        if (dbAdmin != null) {//账号已存在
            throw new CustomException("500", "账号已存在，请更换别的账号");
        }
        if (StrUtil.isBlank(admin.getPassword())) { //没填密码 默认密码
            admin.setPassword("123456");
        }
        if (StrUtil.isBlank(admin.getName())) { //没填名字 默认账号名字
            admin.setName(admin.getUsername());
        }
        //一定要设置角色
        admin.setRole("ADMIN");
        adminMapper.insert(admin);
    }
    public void update(Admin admin) { //改
        adminMapper.updateById(admin);
    }
    public void deleteById(Integer id) { //删单
        adminMapper.deleteById(id);
    }
    public void deleteBatch(List<Integer> ids) { //删多
        for (Integer id : ids) {
            this.deleteById(id);
        }
    }

    public List<Admin> selectAll(Admin admin) {
        // 可以写额外的业务操作
        return adminMapper.selectAll(admin);
    }
    public Admin selectById(Integer id) {
        return adminMapper.selectById(id);
    }
    //实现分页查询
    public PageInfo<Admin> selectPage(Admin admin,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> list = adminMapper.selectAll(admin);
        return PageInfo.of(list);
    }
    public List<Admin> selectList(Admin admin) {
        System.out.println(admin);
        return null;
    }

    public Admin login(Account account) {
        String username = account.getUsername();
        Admin dbAdmin = adminMapper.selectByUsername(username);
        if (dbAdmin == null) {// 没查询到的话
            throw new CustomException("500","账号不存在");
        }
        //数据库存在帐号了 再去判断密码
        String password = account.getPassword();
        if (!dbAdmin.getPassword().equals(password)) {
            throw new CustomException("500","账号或密码错误");
        }
        return dbAdmin;
    }
    //注册直接调用新增的逻辑
    public void register(Admin admin) {
        this.add(admin);
    }

    public void updatePassword(Account account) {
        Integer id = account.getId();
        Admin admin = this.selectById(id);
        if (!admin.getPassword().equals(account.getPassword())) {//如果原密码不匹配
            throw new CustomException("500","对不起，原密码错误");
        }
        admin.setPassword(account.getNewPassword());//设置新密码
        this.update(admin);
    }
}
