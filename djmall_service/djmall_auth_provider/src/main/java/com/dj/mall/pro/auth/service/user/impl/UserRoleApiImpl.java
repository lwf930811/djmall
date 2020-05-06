package com.dj.mall.pro.auth.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.entity.auth.user.UserRoleEntity;
import com.dj.mall.mapper.auth.user.UserRoleMapper;
import com.dj.mall.pro.auth.service.user.UserRoleApi;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.pro.auth.user_role
 * @ClassName: UserRoleApiImpl
 * @Author: Liuwf
 * @Description: 用户角色关联
 * @Date: 2020/3/28 17:02
 * @Version: 1.0
 */
@Service
public class UserRoleApiImpl extends ServiceImpl<UserRoleMapper, UserRoleEntity> implements UserRoleApi {


}
