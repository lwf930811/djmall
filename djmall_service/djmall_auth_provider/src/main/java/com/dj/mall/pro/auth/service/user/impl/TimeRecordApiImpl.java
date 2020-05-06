package com.dj.mall.pro.auth.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.entity.auth.user.TimeRecordEntity;
import com.dj.mall.mapper.auth.user.TimeRecordMapper;
import com.dj.mall.pro.auth.service.user.TimeRecordApi;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.pro.auth.service.user.impl
 * @ClassName: TimeRecordApiImpl
 * @Author: Liuwf
 * @Description: 时间记录实现
 * @Date: 2020/4/3 15:21
 * @Version: 1.0
 */
@Service
public class TimeRecordApiImpl extends ServiceImpl<TimeRecordMapper, TimeRecordEntity> implements TimeRecordApi {


}
