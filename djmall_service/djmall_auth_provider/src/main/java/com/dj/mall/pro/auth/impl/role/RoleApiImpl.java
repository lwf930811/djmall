package com.dj.mall.pro.auth.impl.role;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.api.auth.role.RoleApi;
import com.dj.mall.api.cmpt.RedisApi;
import com.dj.mall.entity.auth.resource.ResourceEntity;
import com.dj.mall.entity.auth.role.RoleEntity;
import com.dj.mall.entity.auth.role.RoleResourceEntity;
import com.dj.mall.entity.auth.user.UserRoleEntity;
import com.dj.mall.mapper.auth.role.RoleMapper;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.constant.RedisConstant;
import com.dj.mall.model.constant.SystemConstant;
import com.dj.mall.model.dto.auth.resource.ResourceDTOResp;
import com.dj.mall.model.dto.auth.role.RoleDTOReq;
import com.dj.mall.model.dto.auth.role.RoleDTOResp;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.pro.auth.service.role.RoleResourceApi;
import com.dj.mall.pro.auth.service.user.UserRoleApi;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleApiImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleApi {
    @Autowired
    private UserRoleApi userRoleApi;
    @Autowired
    private RoleResourceApi roleResourceApi;
    @Reference
    private RedisApi redisApi;
    /**
     * @param
     * @Description:查询用户角色
     * @Author: Liuwf
     * @Date:
     * @return: java.util.List<com.dj.mall.model.dto.auth.role.RoleDTOResp>
     **/
    @Override
    public List<RoleDTOResp> queryRoleList() throws Exception, BusinessException {
        QueryWrapper<RoleEntity> roleEntityQueryWrapper = new QueryWrapper<>();
        roleEntityQueryWrapper.eq("is_del", SystemConstant.NOT_DELETE_IS_DEL);
        List<RoleEntity> roleEntityList = this.list(roleEntityQueryWrapper);
        return DozerUtil.mapList(roleEntityList, RoleDTOResp.class);
    }

    /**
     * @param roleName :
     * @Description:查询角色名
     * @Author: Liuwf
     * @Date:
     * @return: com.dj.mall.model.dto.auth.role.RoleDTOResp
     **/
    @Override
    public Boolean findRoleByRoleName(String roleName) throws Exception, BusinessException {
        QueryWrapper<RoleEntity> roleEntityQueryWrapper = new QueryWrapper<>();
        roleEntityQueryWrapper.eq("role_name", roleName);
        RoleEntity roleEntity = this.getOne(roleEntityQueryWrapper);
        return roleEntity == null ? true : false;
    }

    /**
     * @param roleDTOReq :
     * @Description:增加角色
     * @Author: Liuwf
     * @Date:
     * @return: void
     **/
    @Override
    public void addRole(RoleDTOReq roleDTOReq) throws Exception, BusinessException {
        RoleEntity roleEntity = DozerUtil.map(roleDTOReq, RoleEntity.class);
        this.save(roleEntity);
//        更新redis
        redisApi.pushHash(RedisConstant.ROLE_RESOURCE_ALL,RedisConstant.ROLE_RESOURCE + roleEntity.getId(), null);
    }

    /**
     * @param id :
     * @Description:根据id查找对象
     * @Author: Liuwf
     * @Date:
     * @return: com.dj.mall.model.dto.auth.role.RoleDTOResp
     **/
    @Override
    public RoleDTOResp findRoleById(Integer id) throws Exception, BusinessException {
        QueryWrapper<RoleEntity> roleEntityQueryWrapper = new QueryWrapper<>();
        roleEntityQueryWrapper.eq("id", id);
        return DozerUtil.map(this.getOne(roleEntityQueryWrapper),RoleDTOResp.class);
    }

    /**
     * @param roleDTOReq :
     * @Description:修改角色
     * @Author: Liuwf
     * @Date:
     * @return: void
     **/
    @Override
    public void updateRole(RoleDTOReq roleDTOReq) throws Exception, BusinessException {
        this.updateById(DozerUtil.map(roleDTOReq,RoleEntity.class));
    }

    /**
     * @Description:删除角色相关联的
     * @Author: Liuwf
     * @Date:
     * @return: void
     **/
    @Override
    public void delRoleAndUserRoleAndRoleResource(Integer id) throws Exception, BusinessException {
//        删除角色
        UpdateWrapper<RoleEntity> roleEntityUpdateWrapper = new UpdateWrapper<>();
        roleEntityUpdateWrapper.set("is_del",SystemConstant.DELETE_IS_DEL);
        roleEntityUpdateWrapper.eq("id",id);
        this.update(roleEntityUpdateWrapper);
//        删除用户角色
        UpdateWrapper<UserRoleEntity> userRoleEntityUpdateWrapper = new UpdateWrapper<>();
        userRoleEntityUpdateWrapper.set("is_del",SystemConstant.DELETE_IS_DEL);
        roleEntityUpdateWrapper.eq("role_id",id);
        userRoleApi.update(userRoleEntityUpdateWrapper);
//        删除角色资源
        UpdateWrapper<RoleResourceEntity> roleResourceEntityUpdateWrapper = new UpdateWrapper<>();
        roleResourceEntityUpdateWrapper.set("is_del",SystemConstant.DELETE_IS_DEL);
        roleResourceEntityUpdateWrapper.eq("role_id",id);
        roleResourceApi.update(roleResourceEntityUpdateWrapper);
//        更新redis
        redisApi.delHash(RedisConstant.ROLE_RESOURCE_ALL,RedisConstant.ROLE_RESOURCE + id);

    }

    /**
     * @param roleId :
     * @Description:根据角色id获取对应的资源
     * @Author: Liuwf
     * @Date:
     * @return: java.util.List<com.dj.mall.model.dto.auth.resource.ResourceDTOResp>
     **/
    @Override
    public List<ResourceDTOResp> getRoleResource(Integer roleId) throws Exception, BusinessException {
        List<ResourceEntity> roleResource = getBaseMapper().getRoleResource(roleId);
        return DozerUtil.mapList(roleResource, ResourceDTOResp.class);
    }

    /**
     * @param roleId      :
     * @param resourceIds :
     * @Description:关联角色资源
     * @Author: Liuwf
     * @Date:
     * @return: void
     **/
    @Override
    public void saveRoleAndResource(Integer roleId, Integer[] resourceIds) throws Exception, BusinessException {
//        先删除
        QueryWrapper<RoleResourceEntity> roleResourceEntityQueryWrapper = new QueryWrapper<>();
        roleResourceEntityQueryWrapper.eq("role_id",roleId);
        roleResourceApi.remove(roleResourceEntityQueryWrapper);
//      后批量新增
        ArrayList<RoleResourceEntity> resourceEntityArrayList = new ArrayList<>();
        for (Integer resourceId : resourceIds) {
            RoleResourceEntity roleResourceEntity = new RoleResourceEntity();
            roleResourceEntity.setRoleId(roleId);
            roleResourceEntity.setResourceId(resourceId);
            roleResourceEntity.setIsDel(SystemConstant.NOT_DELETE_IS_DEL);
            resourceEntityArrayList.add(roleResourceEntity);
        }
        roleResourceApi.saveBatch(resourceEntityArrayList);
//        更新redis
        redisApi.pushHash(RedisConstant.ROLE_RESOURCE_ALL,RedisConstant.ROLE_RESOURCE + roleId,getRoleResource(roleId));

    }

}
