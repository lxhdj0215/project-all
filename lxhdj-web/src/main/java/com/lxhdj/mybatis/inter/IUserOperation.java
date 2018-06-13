package com.lxhdj.mybatis.inter;

import java.util.List;

import com.lxhdj.mybatis.model.Article;
import com.lxhdj.mybatis.model.User;

public interface IUserOperation {
    User selectUserByID(int id);

    List<User> selectUsers(String userName);

    void addUser(User user);

    void updateUser(User user);

    boolean deleteUser(int id);

    List<Article> getUserArticles(int id);

}
