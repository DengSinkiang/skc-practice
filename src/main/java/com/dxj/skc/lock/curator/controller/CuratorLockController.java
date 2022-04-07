package com.dxj.skc.lock.curator.controller;
import com.dxj.skc.lock.curator.util.DistributedLockByCurator;
import com.dxj.skc.util.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sinkiang
 * @date 2022/4/7 10:32
 */
@RestController
@RequestMapping("/hello")
public class CuratorLockController {

    private final DistributedLockByCurator distributedLockByCurator;

    private final static String PATH = "test";

    public CuratorLockController(DistributedLockByCurator distributedLockByCurator) {
        this.distributedLockByCurator = distributedLockByCurator;
    }

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
