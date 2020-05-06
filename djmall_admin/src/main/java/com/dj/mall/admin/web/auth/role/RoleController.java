package com.dj.mall.admin.web.auth.role;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.auth.role.RoleVOReq;
import com.dj.mall.admin.vo.auth.role.RoleVOResp;
import com.dj.mall.api.auth.resource.ResourceApi;
import com.dj.mall.api.auth.role.RoleApi;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.constant.PermissionsCode;
import com.dj.mall.model.dto.auth.resource.ResourceDTOResp;
import com.dj.mall.model.dto.auth.resource.TreeData;
import com.dj.mall.model.dto.auth.role.RoleDTOReq;
import com.dj.mall.model.dto.auth.role.RoleDTOResp;
import com.dj.mall.model.util.DozerUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.admin.web.auth.role
 * @ClassName: RoleController
 * @Author: Liuwf
 * @Description: 角色视图
 * @Date: 2020/3/30 23:34
 * @Version: 1.0
 */
@RestController
@RequestMapping("/auth/role/")
public class RoleController {
    @Reference
    private RoleApi roleApi;
    @Reference
    private ResourceApi resourceApi;

    /**
     * @Description:角色list
     * @Author: Liuwf
     * @Date:

     * @return: com.dj.mall.model.base.ResultModel
     **/
    @GetMapping("roleList")
    @RequiresPermissions(value = PermissionsCode.ROLE_MANAGE)
    public ResultModel roleList() throws Exception {
        List<RoleDTOResp> roleDTOResps = roleApi.queryRoleList();
        return new ResultModel().success(DozerUtil.mapList(roleDTOResps, RoleVOResp.class));
    }

    /**
     * @Description:查找角色
     * @Author: Liuwf
     * @Date:
     * @param roleName:
     * @return: boolean
     **/
    @GetMapping("findRoleByRoleName")
    public boolean getUserName(String roleName) throws Exception{
        Boolean roleByRoleName = roleApi.findRoleByRoleName(roleName);
        return roleByRoleName ;
    }

    /**
     * @Description:角色增加
     * @Author: Liuwf
     * @Date:
     * @param roleVOReq:
     * @return: com.dj.mall.model.base.ResultModel
     **/
    @PostMapping("addRoleName")
    @RequiresPermissions(value = PermissionsCode.ROLE_ADD_BTN)
    public ResultModel addRole(RoleVOReq roleVOReq) throws Exception{
        roleApi.addRole(DozerUtil.map(roleVOReq, RoleDTOReq.class));
        return new ResultModel().success(true);
    }

    /**
     * @Description:角色修改
     * @Author: Liuwf
     * @Date:
     * @param roleVOReq:
     * @return: com.dj.mall.model.base.ResultModel
     **/
    @PutMapping("updateRole")
    public ResultModel updateRole(RoleVOReq roleVOReq) throws Exception{
        roleApi.updateRole(DozerUtil.map(roleVOReq,RoleDTOReq.class));
        return new ResultModel().success(true);
    }
    
    /**
     * @Description:删除
     * @Author: Liuwf
     * @Date:
     * @param id:
     * @return: com.dj.mall.model.base.ResultModel
     **/
    @PutMapping("delRole")
    public ResultModel delRole(Integer id) throws Exception{
        roleApi.delRoleAndUserRoleAndRoleResource(id);
        return new ResultModel().success(true);
    }

    @GetMapping("getRoleResource/{roleId}")
    public ResultModel getRoleResource(@PathVariable Integer roleId) throws Exception{
        //        获取全部资源
        List<ResourceDTOResp> resourceList = resourceApi.queryResourceList();
//        获取角色关联的资源
        List<ResourceDTOResp> roleResourceList = roleApi.getRoleResource(roleId);
//        获取节点数据
        ArrayList<TreeData> treeDataList = new ArrayList<TreeData>();
        resourceList.forEach(resource -> {
            TreeData treeData = new TreeData();
            treeData.setId(resource.getResourceId());
            treeData.setPId(resource.getPId());
            treeData.setResourceName(resource.getResourceName());
            for (ResourceDTOResp resourceDTOResp : roleResourceList) {
                if(resourceDTOResp.getResourceId().equals(resource.getResourceId())){
                    treeData.setChecked(true);
                    break;
                }
            }
            treeDataList.add(treeData);
        });
        return new ResultModel().success(treeDataList);
    }

    /**
     * @Description:关联资源
     * @Author: Liuwf
     * @Date:
     * @param roleId:
     * @param resourceIds:
     * @return: com.dj.mall.model.base.ResultModel
     **/
    @PostMapping("saveRoleResource/{roleId}")
    public ResultModel saveRoleResource(@PathVariable Integer roleId, Integer[] resourceIds ) throws Exception{
        roleApi.saveRoleAndResource(roleId,resourceIds);
        return new ResultModel().success();
    }

}
