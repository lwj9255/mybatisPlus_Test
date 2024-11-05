package com.hhu.mybatisplustest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhu.mybatisplustest.mapper.UserMapper;
import com.hhu.mybatisplustest.pojo.User;
import com.hhu.mybatisplustest.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MybatisplusTestApplicationTests3 {
    @Resource
    private UserMapper userMapper;
    @Resource
    private IUserService userservice;

    @Test
    void queryPage() {
        Page<User> page = new Page<>(1,5);
        Page<User> userPage = userMapper.selectPage(page, null);
        System.out.println("userPage.getCurrent() = " + userPage.getCurrent());
        System.out.println("userPage.getSize() = " + userPage.getSize());
        System.out.println("userPage.getTotal() = " + userPage.getTotal());
        System.out.println("userPage.getPages() = " + userPage.getPages());
        System.out.println("userPage.hasPrevious() = " + userPage.hasPrevious());
        System.out.println("userPage.hasNext() = " + userPage.hasNext());
    }
}
