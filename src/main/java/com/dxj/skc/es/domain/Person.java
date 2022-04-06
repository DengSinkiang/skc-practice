package com.dxj.skc.es.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jianghehe
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
}