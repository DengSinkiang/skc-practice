package com.dxj.skc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dxj.skc.domain.dto.UserDTO;
import com.dxj.skc.domain.entity.User;
import com.dxj.skc.exception.SkException;
import com.dxj.skc.mapstruct.UserMapper;
import com.dxj.skc.service.UserService;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: dengxj29231
 * @Date: 2020/7/28 16:52
 * @CopyRight: 2020 sk-admin all rights reserved.
 */
@SpringBootTest(classes = SkcApplication.class)
public class SkcTest {
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private UserService userService;
    @Test
    void testLoad() {
        System.out.println("hello skc");
    }

    @Test
    void testSend() throws InterruptedException {
        String msg = "hello, Spring boot amqp";
        this.amqpTemplate.convertAndSend("spring.test.exchange","a.b", msg);
        // 等待10秒后再结束
        Thread.sleep(10000);
    }
    @Test
    void testSaveUser() {
        long time1 = System.currentTimeMillis();
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            User user = new User();
            user.setAge(25 + i);
            user.setPassword("123456" + i);
            user.setUsername("dxj" + i);
            user.setEmail("dxj"  + i + "@qq.com");
            list.add(user);
        }
        List<List<User>> subList = Lists.partition(list, 1000);
        boolean saveFlag;
        for (List<User> sub : subList) {
            saveFlag = userService.saveBatch(sub);
            if (!saveFlag) {
                throw new SkException("插入失败！");
            }
        }
        long time2 = System.currentTimeMillis();
        long time = time2 - time1;
        System.out.println("耗时：" + time);

    }
    @Test
    void testQueryUser() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", "dxj0");

        Page<User> pageObj = new Page<>(1, 10);
        List<User> page = userService.page(pageObj, queryWrapper).getRecords();
        for (User user : page) {
            System.out.println("userId:" + user.getId());
        }
    }
    @Test
    void testMapStruct() {
        User user = new User();
        user.setUsername("sinkiang");
        user.setPassword("123456");
        user.setAge(22);
        user.setEmail("123456@qq.com");
        UserDTO userDTO = Mappers.getMapper(UserMapper.class).userToUserDTO(user);
        System.out.println(userDTO);
    }
}
