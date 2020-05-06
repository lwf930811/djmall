package com.dj.mall.admin.web.auth.resource;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.mall.api.auth.resource.ResourceApi;
import com.dj.mall.model.constant.PermissionsCode;
import com.dj.mall.model.constant.SystemConstant;
import com.dj.mall.model.dto.auth.resource.ResourceDTOResp;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.admin.web.auth.resource
 * @ClassName: ResourcePageController
 * @Author: Liuwf
 * @Description: 资源视图
 * @Date: 2020/4/2 20:03
 * @Version: 1.0
 */
@Controller
@RequestMapping("/auth/resource/")
public class ResourcePageController {
    @Reference
    private ResourceApi resourceApi;

    /**
     * @Description:资源展示视图
     * @Author: Liuwf
     * @Date:
     * @return: java.lang.String
     **/
    @RequiresPermissions(value = PermissionsCode.RESOURCE_MANAGE)
    @RequestMapping("toList")
    public String toShow(){
        return "auth/resource/resource_list";
    }

    /**
     * @Description:资源新增
     * @Author: Liuwf
     * @Date:
     * @param pId:
     * @param model:
     * @return: java.lang.String
     **/
    @RequestMapping("toAdd/{pId}")
    @RequiresPermissions(value = PermissionsCode.RESOURCE_ADD_BTN)
    public String toAdd(@PathVariable Integer pId, Model model) throws Exception {
        ResourceDTOResp resource = resourceApi.querNameByPId(pId);
        model.addAttribute("resource", resource);
        model.addAttribute("pId",pId);
        return "auth/resource/resource_add";
    }

    /**
     * @Description:资源修改
     * @Author: Liuwf
     * @Date:
     * @param id:
     * @param model:
     * @return: java.lang.String
     **/
    @RequestMapping("toUpdate/{id}")
    @RequiresPermissions(value = PermissionsCode.RESOURCE_UPDATE_BTN)
    public String toUpdate(@PathVariable Integer id,Model model) throws Exception {
        ResourceDTOResp resource = resourceApi.queryResourceById(id);
        model.addAttribute("resource",resource);
        model.addAttribute("id",id);
        return "auth/resource/resource_update";
    }


}
