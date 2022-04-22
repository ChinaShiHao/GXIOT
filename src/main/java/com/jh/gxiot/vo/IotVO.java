package com.jh.gxiot.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @project GxIot
 * @description Iot统一返回数据
 * @date 2022/4/1 17:56:28
 */
@Data
public class IotVO {

    private String statusCode;
    private String message;
    private List<IotPointVo> data;
}
