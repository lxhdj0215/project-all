package com.lxhdj.mybatis;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lxhdj.mybatis.inter.IUserOperation;
import com.lxhdj.mybatis.model.Article;
import com.lxhdj.mybatis.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-config.xml"})
public class UserControllerTest {

    @Resource
    private IUserOperation userOperation;

    @Test
    public void selectUsers() throws Exception {
        List<User> list = userOperation.selectUsers("wgj");
        for (User user : list) {
            System.out.println(user.getId());
        }
    }

    @Test
    public void selectUserByID() throws Exception {
        User user = userOperation.selectUserByID(1);
        System.out.println(user.getUserName());
    }

    @Test
    public void getUserArticles() throws Exception {
        List<Article> list = userOperation.getUserArticles(1);
        for (Article article : list) {
            System.out.println(article.getContent());
            System.out.println(article.getUser().getUserName());
        }
    }

}
