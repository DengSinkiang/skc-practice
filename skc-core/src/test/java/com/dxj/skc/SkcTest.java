package com.dxj.skc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dxj.skc.common.exception.SkException;
import com.dxj.skc.core.domain.User;
import com.dxj.skc.core.domain.dto.UserDTO;
import com.dxj.skc.core.mapstruct.UserMapper;
import com.dxj.skc.core.service.UserService;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author sinkiang
 * @date 2022/4/7 10:32
 */
@SpringBootTest(classes = SkcApplication.class)
public class SkcTest {

    @Autowired
    private UserService userService;
    @Resource
    private ThreadPoolTaskExecutor asyncTaskExecutor;

    @Test
    void testLoad() {
        System.out.println("hello skc");
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
    @Test
    public void test() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int result = 100;
            System.out.println("第一次运算：" + result);
            return result;
        }, asyncTaskExecutor).thenApply(number -> {
            int result = number * 3;
            System.out.println("第二次运算：" + result);
            return result;
        });

        CompletableFuture<Integer> future1 = CompletableFuture
                .supplyAsync(() -> {
                    int number = new Random().nextInt(10);
                    System.out.println("任务1结果：" + number);
                    return number;
                }, asyncTaskExecutor);
        CompletableFuture<Integer> future2 = CompletableFuture
                .supplyAsync(() -> {
                    int number = new Random().nextInt(10);
                    System.out.println("任务2结果：" + number);
                    return number;
                }, asyncTaskExecutor);
        CompletableFuture<Integer> result = future1
                .thenCombine(future2, Integer::sum);
        System.out.println("组合后结果：" + result.join());

        CompletableFuture<String> future11 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future11完成");
            return "future11完成";
        }, asyncTaskExecutor);

        CompletableFuture<String> future22 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future22完成");
            return "future22完成";
        }, asyncTaskExecutor);

        CompletableFuture<Void> combindFuture = CompletableFuture.allOf(future11, future22);
        combindFuture.join();
    }
}
