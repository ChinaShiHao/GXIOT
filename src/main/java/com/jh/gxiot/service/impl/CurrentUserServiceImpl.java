package com.jh.gxiot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jh.gxiot.entity.CurrentUser;
import com.jh.gxiot.mapper.CurrentUserMapper;
import com.jh.gxiot.service.CurrentUserService;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @version 1.0
 * @project GxIot
 * @description 当前班次班别接口实现
 * @date 2022/4/2 11:14:41
 */
@Service
public class CurrentUserServiceImpl extends ServiceImpl<CurrentUserMapper, CurrentUser> implements CurrentUserService {
}
