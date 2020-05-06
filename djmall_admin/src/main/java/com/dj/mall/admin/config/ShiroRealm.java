package com.dj.mall.admin.config;
import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.api.cmpt.RedisApi;
import com.dj.mall.model.constant.RedisConstant;
import com.dj.mall.model.constant.SystemConstant;
import com.dj.mall.model.dto.auth.resource.ResourceDTOResp;

import com.dj.mall.model.dto.auth.user.UserDTOResp;
import lombok.SneakyThrows;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ShiroRealm extends AuthorizingRealm {
    @Reference
    private RedisApi redisApi;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //获取当前登录用户权限信息
        Session session = SecurityUtils.getSubject().getSession();
        UserDTOResp userDTOResp = (UserDTOResp) session.getAttribute(SystemConstant.USER_SESSION);
        List<ResourceDTOResp> permissionList = redisApi.getHash(RedisConstant.ROLE_RESOURCE_ALL, RedisConstant.ROLE_RESOURCE + userDTOResp.getRoleId());
        permissionList.forEach(permission ->
            simpleAuthorizationInfo.addStringPermission(permission.getResourceCode()));
        return simpleAuthorizationInfo;
    }

    /**
     * 认证-登录
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @SneakyThrows
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException{
        // 得到用户名
        String username = (String) authenticationToken.getPrincipal();
        // 得到密码
        String password = new String((char[]) authenticationToken.getCredentials());
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
