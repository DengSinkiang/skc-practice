package com.dxj.skc.es.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sinkiang
 * @date 2022/4/7 10:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String id;
    private String nickname;
    private String name;
    private Integer age;
    private Boolean sex;
    private String birthday;
}