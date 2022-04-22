package com.jh.gxiot.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jh.gxiot.constant.GrindingHaltConstant;
import com.jh.gxiot.constant.IOTConstant;
import com.jh.gxiot.constant.OkhttpMethodEnum;
import com.jh.gxiot.entity.CurrentUser;
import com.jh.gxiot.entity.GrindingHalt;
import com.jh.gxiot.service.CurrentUserService;
import com.jh.gxiot.service.GrindingHaltService;
import com.jh.gxiot.utils.OkHttpUtil;
import com.jh.gxiot.vo.IotPointVo;
import com.jh.gxiot.vo.IotVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * @author Administrator
 * @version 1.0
 * @project GxIot
 * @description 获取IOT数据
 * @date 2022/3/30 18:08:05
 */
@Component
@Slf4j
public class ProcessIot {
    @Resource
    private GrindingHaltService grindingHaltService;
    @Resource
    private CurrentUserService currentUserService;





    @Scheduled(cron = "*/2 * * * * ?")
    @Async("iotExecutor")
    public void getIOT() throws IOException, InterruptedException {

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pointCode",IOTConstant.IOT_POINT_CODE);
        jsonArray.add(jsonObject);

        String json = OkHttpUtil.PostJson(IOTConstant.IOT_URL, String.valueOf(jsonArray), OkhttpMethodEnum.POST_METHOD);
        if (!"".equals(json)) {
            IotVO iotVO = JSON.parseObject(json, IotVO.class);
            try {
                for (IotPointVo pointVo : iotVO.getData()) {
                    //停机开始
                    if (!GrindingHaltConstant.IS_STOP && "false".equals(pointVo.getPointInfo_LastValue())){
                        CurrentUser currentUser = currentUserService.getOne(new QueryWrapper<CurrentUser>().eq("ZONE", "RM"));
                        GrindingHalt grindingHalt = new GrindingHalt();
                        grindingHalt.setStopArea("2#修磨");
                        grindingHalt.setStopStart(new Date());
                        grindingHalt.setStopEnd(new Date());
                        grindingHalt.setStopCreate(new Date());
                        grindingHalt.setStopTime(0);
                        grindingHalt.setStopFlag("N");
                        grindingHalt.setStopInfo("");
                        grindingHalt.setStopShift(currentUser.getProdShift());
                        grindingHalt.setStopCrew(currentUser.getProdCrew());
                        grindingHaltService.save(grindingHalt);
                        GrindingHaltConstant.IS_STOP = true;
                    }
                    else  if (GrindingHaltConstant.IS_STOP && "true".equals(pointVo.getPointInfo_LastValue())){
                        //查找最新的停机记录
                        List<GrindingHalt> halts = grindingHaltService.list(new QueryWrapper<GrindingHalt>().eq("STOP_FLAG", "N").orderBy(true,false,"ID"));
                        if (halts != null && halts.size() > 0){
                            halts.forEach((item)->{
                                item.setStopFlag("Y");
                            });
                            GrindingHalt grindingHalt = halts.get(halts.size() - 1);
                            grindingHalt.setStopEnd(new Date());
                          //  long mills = (grindingHalt.getStopEnd().getTime() - grindingHalt.getStopStart().getTime());
                            grindingHalt.setStopTime((int) ((grindingHalt.getStopEnd().getTime() - grindingHalt.getStopStart().getTime())/1000));
                            grindingHaltService.updateBatchById(halts);
                        }
                        GrindingHaltConstant.IS_STOP = false;
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            //log.info(iotVO.toString());
        }
    }

}
