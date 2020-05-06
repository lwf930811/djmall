package com.dj.mall.pro.auth.task;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.api.auth.role.RoleApi;
import com.dj.mall.api.cmpt.RedisApi;
import com.dj.mall.model.constant.RedisConstant;
import com.dj.mall.model.dto.auth.resource.ResourceDTOResp;
import com.dj.mall.model.dto.auth.role.RoleDTOResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.pro.auth.task
 * @ClassName: InitAuth
 * @Author: Liuwf
 * @Description: 初始化认证权限信息
 * @Date: 2020/4/22 13:12
 * @Version: 1.0
 */
@Component
public class InitAuth {
    @Autowired
    private RoleApi roleApi;
    @Reference
    private RedisApi redisApi;

    /**
     * @Description:初始化权限
     * @Author: Liuwf
     * @Date:
     * @param applicationReadyEvent:
     * @return: void
     **/
    @EventListener(ApplicationReadyEvent.class)
    public void initRoleResource(ApplicationReadyEvent applicationReadyEvent){
        try {
//            获取全部的角色资源
            List<RoleDTOResp> roleList = roleApi.queryRoleList();
            roleList.forEach(roleDTOResp -> {
                try {
                    List<ResourceDTOResp> resourceDTORespList = roleApi.getRoleResource(roleDTOResp.getRoleId());
//                    存入redis
                    redisApi.pushHash(RedisConstant.ROLE_RESOURCE_ALL, RedisConstant.ROLE_RESOURCE + roleDTOResp.getRoleId(), resourceDTORespList);
                }catch (Exception e){
                    e.printStackTrace();
                }

            });
        }catch (Exception e){
            e.printStackTrace();
        }


    }


}
