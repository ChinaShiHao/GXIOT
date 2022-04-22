package com.jh.gxiot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jh.gxiot.entity.CurrentUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Administrator
 * @version 1.0
 * @project GxIot
 * @description 当前班次班别Mapper接口
 * @date 2022/4/2 11:12:42
 */
@Mapper
public interface CurrentUserMapper extends BaseMapper<CurrentUser> {
}
