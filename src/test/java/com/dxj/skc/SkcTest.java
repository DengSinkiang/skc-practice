package com.dxj.skc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dxj.skc.domain.dto.UserDTO;
import com.dxj.skc.domain.entity.User;
import com.dxj.skc.domain.vo.PageVo;
import com.dxj.skc.exception.SkException;
import com.dxj.skc.mapstruct.UserMapper;
import com.dxj.skc.service.UserService;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Description:
 * @Author: dengxj29231
 * @Date: 2020/7/28 16:52
 * @CopyRight: 2020 sk-admin all rights reserved.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SkcApplication.class)
public class SkcTest {
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
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
        long time1 = System.currentTimeMillis();
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            User user = new User();
            user.setAge(25);
            user.setPassword("123456");
            user.setUsername("dxj");
            user.setCreateBy("admin");
            user.setCreateTime(LocalDateTime.now());
            user.setDelFlag(0);
            user.setUpdateBy("dxj");
            user.setUpdateTime(LocalDateTime.now());
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
    @Test
    public void testMapStruct() {
        User user = new User();
        user.setUsername("sinkiang");
        user.setPassword("123456");
        user.setAge(22);
        user.setEmail("123456@qq.com");
        UserDTO userDTO = userMapper.toDto(user);
        System.out.println(userDTO);
    }
}
