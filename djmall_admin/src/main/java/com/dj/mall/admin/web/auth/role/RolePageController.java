package com.dj.mall.admin.web.auth.role;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.auth.role.RoleVOResp;
import com.dj.mall.api.auth.resource.ResourceApi;
import com.dj.mall.api.auth.role.RoleApi;
import com.dj.mall.model.constant.PermissionsCode;
import com.dj.mall.model.dto.auth.role.RoleDTOResp;
import com.dj.mall.model.util.DozerUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.admin.web.auth.role
 * @ClassName: RolePageController
 * @Author: Liuwf
 * @Description: 角色视图
 * @Date: 2020/3/30 23:35
 * @Version: 1.0
 */
@RequestMapping("/auth/role/")
@Controller
public class RolePageController {
   @Reference
    private RoleApi roleApi;
   @Reference
   private ResourceApi resourceApi;

    /**
     * @Description:展示页面
     * @Author: Liuwf
     * @Date:

     * @return: java.lang.String
     **/
    @RequestMapping("toList")
    @RequiresPermissions(value = PermissionsCode.ROLE_MANAGE)
    public String toShow(){
        return "auth/role/role_list";
    }
    /**
     * @Description:增加页面
     * @Author: Liuwf
     * @Date:

     * @return: java.lang.String
     **/
    @RequestMapping("toAdd")
    @RequiresPermissions(value = PermissionsCode.ROLE_ADD_BTN)
    public String toAdd(){
        return "auth/role/role_add";
    }

    /**
     * @Description:修改页面
     * @Author: Liuwf
     * @Date:
     * @param model:
     * @param id:
     * @return: java.lang.String
     **/
    @RequestMapping("toUpdateRole")
    public String toUpdateRole(Model model, Integer id) throws Exception {
        RoleDTOResp dtoResp = roleApi.findRoleById(id);
        model.addAttribute("role", DozerUtil.map(dtoResp, RoleVOResp.class));
        return "auth/role/role_update";
    }

    @RequestMapping("toRoleResource/{roleId}")
    public String toRoleResource(@PathVariable Integer roleId, Model model){
        model.addAttribute("roleId",roleId);
        return "auth/role/role_resource_list";
    }


}
