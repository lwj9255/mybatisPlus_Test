package com.hhu.mybatisplustest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hhu.mybatisplustest.mapper.UserMapper;
import com.hhu.mybatisplustest.pojo.User;
import com.hhu.mybatisplustest.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.*;

@SpringBootTest
public class MybatisplusTestApplicationTests2 {
    @Resource
    private UserMapper userMapper;
    @Resource
    private IUserService userservice;

    /**
     * 查询用户姓名中包含o，年龄大于20，邮箱不为null的记录
     */
    @Test
    void queryUser_o_20_notnull(){
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.like("name","o")
                .gt("age",20)
                .isNotNull("email");
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    /**
     * 根据年龄升序然后根据id降序
     */
    @Test
    void queryUserByOrder() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("age")
                .orderByDesc("id");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    /**
     * 删除所有年龄小于18岁的用户
     */
    @Test
    void deleteUser() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.le("age",18);
        int i = userMapper.delete(wrapper);
        System.out.println(i);
    }

    /**
     * 查询出年龄大于20并且姓名中包含的有'o'
     * 或者
     * 邮箱地址为空的记录
     */
    @Test
    void queryUser_gt20_o_nn1() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // SELECT id,name,age,email,is_deleted FROM user
        // WHERE is_deleted=0
        // AND (age > ? AND name LIKE ? OR email IS NOT NULL)
        wrapper.gt("age",20)
                .like("name","o")
                .or() // 默认是通过and连接 显示加上 or()方法表示or连接
                .isNotNull("email");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    @Test
    void queryUser_gt20_o_nn2() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // SELECT id,name,age,email,is_deleted FROM user
        // WHERE is_deleted=0
        // AND ((age > ? AND name LIKE ?) OR (email IS NOT NULL))
        wrapper.and((i) ->{
            i.gt("age",20).like("name","0");
        }).or((i) ->{
            i.isNotNull("email");
        });
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    @Test
    void queryUser_tedinziduan() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age",20)
                .like("name","o")
                .or() // 默认是通过and连接 显示加上 or()方法表示or连接
                .isNotNull("email")
                .select("id","name","age") // 指定特定的字段
        ;
        // 如果用list接收select返回的特定字段，不查的字段会显示null
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
        // selectMaps()返回Map集合列表，通常配合select()使用
        // 避免User对象中没有被查询到的列值为null
        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }

    @Test
    void queryUser_dongtai() {
        String  name = "Tom";
        Integer age = null;
        String email = null;
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(name),"name",name)
                .eq(age!=null && age > 0 ,"age" ,age)
                .eq(StringUtils.isNotBlank(email),"email",email);
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }
}
