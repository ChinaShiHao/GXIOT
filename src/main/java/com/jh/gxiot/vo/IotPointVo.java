package com.jh.gxiot.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @author Administrator
 * @version 1.0
 * @project GxIot
 * @description Iot点
 * @date 2022/4/1 17:57:42
 */
@Data
public class IotPointVo {

    @JSONField(defaultValue = "deviceInfo_SubDeviceCode")
    private String deviceInfo_SubDeviceCode;
    @JSONField(defaultValue = "pointInfo_PointCode")
    private String pointInfo_PointCode;
    @JSONField(defaultValue = "pointInfo_DataType")
    private String pointInfo_DataType;
    @JSONField(defaultValue = "pointInfo_LowLimit")
    private String pointInfo_LowLimit;
    @JSONField(defaultValue = "pointInfo_UpLimit")
    private String pointInfo_UpLimit;
    @JSONField(defaultValue = "pointInfo_SwitchValue")
    private String pointInfo_SwitchValue;
    @JSONField(defaultValue = "pointInfo_Frequency")
    private String pointInfo_Frequency;
    @JSONField(defaultValue = "pointInfo_Status")
    private String pointInfo_Status;
    @JSONField(defaultValue = "pointInfo_LastValue")
    private String pointInfo_LastValue;
    @JSONField(defaultValue = "pointInfo_LastTime",format ="yyyy-MM-dd HH:mm:ss" )
    private Date pointInfo_LastTime;
    @JSONField(defaultValue = "pointAlarm_Status")
    private String pointAlarm_Status;
    @JSONField(defaultValue = "pointAlarm_LastTime")
    private String pointAlarm_LastTime;
    @JSONField(defaultValue = "pointAlarm_StartTime")
    private Object pointAlarm_StartTime;
    @JSONField(defaultValue = "pointInfo_Describe")
    private Object pointInfo_Describe;




}
