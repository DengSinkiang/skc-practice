package com.dxj.skc.datasource.domain;

import com.dxj.skc.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Description:
 * @Author: dengxj29231
 * @Date: 2020/8/13 13:12
 * @CopyRight: 2020 hundsun all rights reserved.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "t_data_source")
public class GenDataSourceConf extends BaseEntity {

    private String name;
    private String userName;
    private String pwd;
    private String jdbcUrl;
}
