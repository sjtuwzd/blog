package com.zhengda.blog.dao;

import com.zhengda.blog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


    User findByUsernameAndPassword(String username, String password);
}
