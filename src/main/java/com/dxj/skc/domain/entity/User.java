package com.dxj.skc.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Description:
 * @Author: dengxj29231
 * @Date: 2020/8/6 16:22
 * @CopyRight: 2020 hundsun all rights reserved.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "t_user")
@TableName("t_user")
@ApiModel(value = "用户")
public class User extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "年龄")
    private int age;
}
