package com.hhu.mybatisplustest.pojo;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
