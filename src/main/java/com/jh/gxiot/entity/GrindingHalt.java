package com.jh.gxiot.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 * @version 1.0
 * @project GxIot
 * @description 修磨停机实体
 * @date 2022/4/1 17:38:22
 */
@TableName("GRINDING_HALT")
@Data
@KeySequence(value = "GRINDING_HALT_SEQ")
public class GrindingHalt implements Serializable {
    private static final Long Uid = 1L;

    @TableId(value = "ID",type = IdType.INPUT)
    private Long id;
    private Date stopStart;
    private Date stopEnd;
    private Integer stopTime;
    private String stopShift;
    private String stopCrew;
    private String stopArea;
    private String stopInfo;
    private String stopFlag;
    private Date stopCreate;


}
