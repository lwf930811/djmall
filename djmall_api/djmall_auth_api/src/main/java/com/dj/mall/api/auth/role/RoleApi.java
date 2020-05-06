package com.dj.mall.api.auth.role;

import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.dto.auth.resource.ResourceDTOResp;
import com.dj.mall.model.dto.auth.role.RoleDTOReq;
import com.dj.mall.model.dto.auth.role.RoleDTOResp;

import java.util.List;

public interface RoleApi {
    /**
     * @Description:查询用户角色
     * @Author: Liuwf
     * @Date:
     * @param
     * @return: java.util.List<com.dj.mall.model.dto.auth.role.RoleDTOResp>
     **/
    List<RoleDTOResp> queryRoleList() throws Exception, BusinessException;

    /**
     * @Description:查询角色名
     * @Author: Liuwf
     * @Date:
     * @param roleName:
     * @return: com.dj.mall.model.dto.auth.role.RoleDTOResp
     **/
    Boolean findRoleByRoleName(String roleName) throws Exception, BusinessException;

    /**
     * @Description:增加角色
     * @Author: Liuwf
     * @Date:
     * @param roleDTOReq:
     * @return: void
     **/
    void addRole(RoleDTOReq roleDTOReq)throws  Exception, BusinessException;

    /**
     * @Description:根据id查找对象
     * @Author: Liuwf
     * @Date:
     * @param id:
     * @return: com.dj.mall.model.dto.auth.role.RoleDTOResp
     **/
    RoleDTOResp findRoleById(Integer id) throws Exception, BusinessException;

    /**
     * @Description:修改角色
     * @Author: Liuwf
     * @Date:
     * @param roleDTOReq:
     * @return: void
     **/
    void updateRole(RoleDTOReq roleDTOReq) throws Exception, BusinessException;

    /**
     * @Description:删除角色相关联的
     * @Author: Liuwf
     * @Date:

     * @return: void
     **/
    void delRoleAndUserRoleAndRoleResource(Integer id)throws Exception,BusinessException;

    /**
     * @Description:根据角色id获取对应的资源
     * @Author: Liuwf
     * @Date:
     * @param roleId:
     * @return: java.util.List<com.dj.mall.model.dto.auth.resource.ResourceDTOResp>
     **/
    List<ResourceDTOResp> getRoleResource(Integer roleId)throws Exception,BusinessException;

    /**
     * @Description:关联角色资源
     * @Author: Liuwf
     * @Date:
     * @param roleId:
     * @param resourceIds:
     * @return: void
     **/
    void saveRoleAndResource(Integer roleId, Integer[] resourceIds) throws Exception,BusinessException;


}
