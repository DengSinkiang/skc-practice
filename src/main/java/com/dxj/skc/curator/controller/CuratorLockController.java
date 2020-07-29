package com.dxj.skc.curator.controller;
import com.dxj.skc.curator.util.DistributedLockByCurator;
import com.dxj.skc.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: Sinkiang
 * @Date: 2020/7/27 11:17
 * @CopyRight: 2020 sk-admin all rights reserved.
 */
@RestController
@RequestMapping("/hello")
public class CuratorLockController {

    @Autowired
    private DistributedLockByCurator distributedLockByCurator;

    private final static String PATH = "test";

    @GetMapping("/lock1")
    public ResultUtils<Boolean> getLock1() {
        boolean flag;
        distributedLockByCurator.acquireDistributedLock(PATH);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = distributedLockByCurator.releaseDistributedLock(PATH);
        return ResultUtils.success(flag);
    }

    @GetMapping("/lock2")
    public ResultUtils<Boolean> getLock2() {
        boolean flag;
        distributedLockByCurator.acquireDistributedLock(PATH);
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = distributedLockByCurator.releaseDistributedLock(PATH);
        return ResultUtils.success(flag);
    }
}
