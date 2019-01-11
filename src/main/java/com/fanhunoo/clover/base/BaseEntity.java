package com.fanhunoo.clover.base;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseEntity implements Serializable {
    //创建时间
    private LocalDateTime createTime;
    //创建人
    private String createPer;
    //更新时间
    private LocalDateTime updateTime;
    //更新人
    private String updatePer;
}
