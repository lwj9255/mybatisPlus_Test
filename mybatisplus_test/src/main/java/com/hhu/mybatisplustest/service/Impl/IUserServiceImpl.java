package com.hhu.mybatisplustest.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhu.mybatisplustest.mapper.UserMapper;
import com.hhu.mybatisplustest.pojo.User;
import com.hhu.mybatisplustest.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class IUserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
