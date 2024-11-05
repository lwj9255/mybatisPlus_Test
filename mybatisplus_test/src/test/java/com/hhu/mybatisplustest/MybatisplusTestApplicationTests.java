package com.hhu.mybatisplustest;

import com.hhu.mybatisplustest.mapper.UserMapper;
import com.hhu.mybatisplustest.pojo.User;
import com.hhu.mybatisplustest.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.*;

@SpringBootTest
public class MybatisplusTestApplicationTests {
    @Resource
    private UserMapper userMapper;

    @Test
    void queryUser() {
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    void addUser() {
        User user = new User();
        user.setAge(18);
        user.setEmail("123@qq.com");
        user.setName("zs");
        System.out.println(userMapper.insert(user));
    }

    @Test
    void updateUser() {
        User user = new User();
        user.setAge(61);
        user.setEmail("321@qq.com");
        user.setName("ls");
        user.setId(1853623072481988609l);
        System.out.println(userMapper.updateById(user));
    }

    @Test
    void deleteUserById() {
        User user = new User();
        user.setAge(61);
        user.setEmail("321@qq.com");
        user.setName("ls");
        user.setId(1853623072481988609l);
        System.out.println(userMapper.deleteById(user));
    }

    @Test
    void deleteUserByBatchId() {
        System.out.println((userMapper.deleteBatchIds(Arrays.asList(1l, 2l, 3l, 4l))));
    }

    @Test
    void deleteUserByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("age", 24);
        map.put("name", "Billie");
        userMapper.deleteByMap(map);
    }

    @Test
    void queryUserById() {
        System.out.println(userMapper.selectById(1));
    }

    @Test
    void queryUserByBatchId() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    void queryUserByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name","Tom");
        map.put("age",28);
        userMapper.selectByMap(map).forEach(System.out::println);
    }

    @Test
    void queryUserForAll(){
        userMapper.selectList(null).forEach(System.out::println);
    }

    @Resource
    private IUserService userservice;

    @Test
    void selectByIdService(){
        System.out.println(userservice.getById(1));
    }

    @Test
    void selectListService(){
        userservice.list().forEach(System.out::println);
    }

    @Test
    void saveByEntity(){
        User user = new User();
        user.setAge(18);
        user.setEmail("123@qq.com");
        user.setName("zs");
        System.out.println("userservice.save(user) = " + userservice.save(user));
    }

    @Test
    void saveBatch(){
        ArrayList<User> users = new ArrayList<>();
        for(int i =0;i < 10;i++){
            User user = new User();
            user.setEmail("111@11.com");
            user.setName("a"+i);
            user.setAge(10+i);
            users.add(user);
        }
        userservice.saveBatch(users);
    }

}
