package com.zhengda.blog.service;

import com.zhengda.blog.po.User;

public interface UserService {

    User checkUser(String username, String password);
}
