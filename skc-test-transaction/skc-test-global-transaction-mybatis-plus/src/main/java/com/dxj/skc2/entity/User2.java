package com.dxj.skc2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author sinkiang
 * @date 2022/4/8 10:06
 */
@Data
@TableName("user2")
public class User2 {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
}
