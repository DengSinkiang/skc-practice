package com.dxj.skc.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 雪花算法工具生成
 * @Author: Sinkiang
 * @Date: 2020/3/27 16:14
 * @CopyRight: 2020 sk-admin all rights reserved.
 */
@Slf4j
public class SnowFlakeUtil {

    private final long id;
    /**
     * 机器标识位数
     */
    private final long workerIdBits = 10L;
    /**
     * 0，并发控制
     */
    private long sequence = 0L;

    private long lastTimestamp = -1L;

    private SnowFlakeUtil(long id) {

        // 机器ID最大值: 1023
        long maxWorkerId = ~(-1L << workerIdBits);
        if (id > maxWorkerId || id < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        this.id = id;
    }

    public synchronized long nextId() {
        long timestamp = timeGen();
        // 毫秒内自增位
        long sequenceBits = 12L;
        if (lastTimestamp == timestamp) {
            // 4095,111111111111,12位
            long sequenceMask = ~(-1L << sequenceBits);
            // 如果上一个timestamp与新产生的相等，则sequence加一(0-4095循环); 对新的timestamp，sequence从0开始
            sequence = sequence + 1 & sequenceMask;
            if (sequence == 0) {
                // 重新生成timestamp
                timestamp = this.tilNextMillis(lastTimestamp);
            }
        } else {
            this.sequence = 0;
        }

        if (timestamp < lastTimestamp) {
            log.error(String.
                    format("clock moved backwards.Refusing to generate id for %d milliseconds", (lastTimestamp - timestamp)));
            return -1;
        }

        lastTimestamp = timestamp;
        // 时间起始标记点，作为基准，一般取系统的最近时间
        long epoch = 1585301167730L;

        // 22
        long timestampLeftShift = sequenceBits + workerIdBits;
        return timestamp - epoch << timestampLeftShift | id << sequenceBits | sequence;
    }

    private static SnowFlakeUtil flowIdWorker = new SnowFlakeUtil(1);

    public static SnowFlakeUtil getFlowIdInstance() {
        return flowIdWorker;
    }

    /**
     * 等待下一个毫秒的到来, 保证返回的毫秒数在参数lastTimestamp之后
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 获得系统当前毫秒数
     */
    private static long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        SnowFlakeUtil snowFlakeUtil;
        for (int i = 0; i < 100; i++) {
            snowFlakeUtil = SnowFlakeUtil.getFlowIdInstance();
            System.out.println(snowFlakeUtil.nextId());
        }
    }
}

