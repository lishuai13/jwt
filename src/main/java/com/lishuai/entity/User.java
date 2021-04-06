package com.lishuai.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author lishuai
 */
@Data
@TableName(value = "user")
public class User {

    @TableId
    private int id;
    private String username;
    private String password;
}
