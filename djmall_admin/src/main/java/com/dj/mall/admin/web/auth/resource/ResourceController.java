package com.dj.mall.admin.web.auth.resource;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.auth.resource.ResourceVoReq;
import com.dj.mall.admin.vo.auth.resource.ResourceVoResp;
import com.dj.mall.api.auth.resource.ResourceApi;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.constant.PermissionsCode;
import com.dj.mall.model.dto.auth.resource.ResourceDTOReq;
import com.dj.mall.model.dto.auth.resource.ResourceDTOResp;
import com.dj.mall.model.util.DozerUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.admin.web.auth.resource
 * @ClassName: ResourceController
 * @Author: Liuwf
 * @Description: 资源视图
 * @Date: 2020/4/2 20:01
 * @Version: 1.0
 */
@RestController
@RequestMapping("/auth/resource/")
public class ResourceController {
    @Reference
    private ResourceApi resourceApi;
    /**
     * @Description:资源list
     * @Author: Liuwf
     * @Date:

     * @return: com.dj.mall.model.base.ResultModel<java.lang.Object>
     **/
    @RequestMapping("ResourceList")
    @RequiresPermissions(value = PermissionsCode.RESOURCE_MANAGE)
    public ResultModel<Object> ResourceList() throws Exception {
        List<ResourceDTOResp> resourceDTOResps = resourceApi.queryResourceList();
        return new ResultModel<>().success(DozerUtil.mapList(resourceDTOResps, ResourceVoResp.class));
    }

    /**
     * @Description:资源去重
     * @Author: Liuwf
     * @Date:
     * @param resourceVoReq:
     * @return: java.lang.Boolean
     **/
    @GetMapping("findByResourceNameOrCode")
    public Boolean findResourceByResourceNameOrCode(ResourceVoReq resourceVoReq) throws Exception {
        Boolean resource = resourceApi.findResourceByResourceNameOrCode(DozerUtil.map(resourceVoReq, ResourceDTOReq.class));
        return resource;
    }

    /**
     * @Description:资源新增
     * @Author: Liuwf
     * @Date:
     * @param resourceVoReq:
     * @return: com.dj.mall.model.base.ResultModel<java.lang.Object>
     **/
    @RequestMapping("addResource")
    @RequiresPermissions(value = PermissionsCode.RESOURCE_ADD_BTN)
    public ResultModel<Object> saveResource(ResourceVoReq resourceVoReq) throws Exception {
        resourceApi.saveResource(DozerUtil.map(resourceVoReq,ResourceDTOReq.class));
        return new ResultModel<>().success();
    }

    /**
     * @Description:资源修改
     * @Author: Liuwf
     * @Date:
     * @param resourceVoReq:
     * @return: com.dj.mall.model.base.ResultModel<java.lang.Object>
     **/
    @RequestMapping("updateResource")
    @RequiresPermissions(value = PermissionsCode.RESOURCE_UPDATE_BTN)
    public ResultModel<Object> updateResource(ResourceVoReq resourceVoReq) throws Exception {
        resourceApi.updateResource(DozerUtil.map(resourceVoReq,ResourceDTOReq.class));
        return new ResultModel<>().success();
    }

    /**
     * @Description:资源删除
     * @Author: Liuwf
     * @Date:
     * @param id:
     * @return: com.dj.mall.model.base.ResultModel<java.lang.Object>
     **/
    @RequestMapping("delRoleResourceById/{id}")
    @RequiresPermissions(value = PermissionsCode.RESOURCE_DELETE_BTN)
    public ResultModel<Object> delRoleResource(@PathVariable Integer id) throws Exception {
        resourceApi.delRoleResourceById(id);
        return new ResultModel<>().success();
    }




}
