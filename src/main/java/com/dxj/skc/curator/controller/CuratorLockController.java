package com.dxj.skc.curator.controller;

import com.dxj.skc.domain.vo.Result;
import com.dxj.skc.curator.util.DistributedLockByCurator;
import com.dxj.skc.util.ResultUtil;
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
    public Result<Boolean> getLock1() {
        boolean flag;
        distributedLockByCurator.acquireDistributedLock(PATH);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = distributedLockByCurator.releaseDistributedLock(PATH);
        return new ResultUtil<Boolean>().setData(flag);
    }

    @GetMapping("/lock2")
    public Result<Boolean> getLock2() {
        boolean flag;
        distributedLockByCurator.acquireDistributedLock(PATH);
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = distributedLockByCurator.releaseDistributedLock(PATH);
        return new ResultUtil<Boolean>().setData(flag);
    }
}
