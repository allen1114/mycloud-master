package com.allencai.mycloud.tmp1.provider.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("t_step1")
@Data
public class Step1 {

    private Long id;
    private String code;
}
