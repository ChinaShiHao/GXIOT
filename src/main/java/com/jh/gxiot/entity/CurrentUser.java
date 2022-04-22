package com.jh.gxiot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 * @version 1.0
 * @project GxIot
 * @description 当前班次班别信息
 * @date 2022/4/2 11:07:18
 */
@Data
@TableName("CURRENT_USER")
public class CurrentUser implements Serializable {
    @TableId(value = "ZONE",type = IdType.INPUT)
    private String zone;
    @TableField("USR_ID")
    private String userId;
    private String prodCrew;
    private String ProdShift;
    private Date toc;
    private Date tom;

}
