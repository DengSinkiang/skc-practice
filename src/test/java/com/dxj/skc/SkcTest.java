package com.dxj.skc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dxj.skc.domain.entity.User;
import com.dxj.skc.domain.vo.PageVo;
import com.dxj.skc.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description:
 * @Author: dengxj29231
 * @Date: 2020/7/28 16:52
 * @CopyRight: 2020 sk-admin all rights reserved.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SkcTest {
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private UserService userService;
    @Test
    public void testLoad() {
        System.out.println("hello skc");
    }
    @Test
    public void testSend() throws InterruptedException {
        String msg = "hello, Spring boot amqp";
        this.amqpTemplate.convertAndSend("spring.test.exchange","a.b", msg);
        // 等待10秒后再结束
        Thread.sleep(10000);
    }
    @Test
    public void testSaveUser() {
        for (int i = 0; i < 50; i++) {
            User user = new User();
            user.setAge(25);
            user.setPassword("123456");
            user.setUsername("dxj");
            user.setCreateBy("admin");
            user.setCreateTime(LocalDateTime.now());
            user.setDelFlag(0);
            user.setUpdateBy("dxj");
            user.setUpdateTime(LocalDateTime.now());
            userService.save(user);
        }

    }
    @Test
    public void testQueryUser() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", "dxj");

        PageVo pageVo = new PageVo();
        pageVo.setPageIndex(2);
        pageVo.setPageSize(10);
        Page<User> pageObj = new Page<>(2, 10);
        List<User> page = userService.page(pageObj, queryWrapper).getRecords();
        for (User user : page) {
            System.out.println("userId:" + user.getId());
        }


    }
}
