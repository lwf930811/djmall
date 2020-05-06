package com.dj.mall.api.auth.resource;

import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.dto.auth.resource.ResourceDTOReq;
import com.dj.mall.model.dto.auth.resource.ResourceDTOResp;

import java.util.List;

public interface ResourceApi {
    /**
     * @Description:查找资源
     * @Author: Liuwf
     * @Date:
     * @return: java.util.List<com.dj.mall.model.dto.auth.resource.ResourceDTOresp>
     **/
    List<ResourceDTOResp> queryResourceList() throws Exception, BusinessException;


    /**
     * @Description:根据资源名或者编码去重
     * @Author: Liuwf
     * @Date:
     * @param resourceDTOReq:
     * @return: com.dj.mall.model.dto.auth.resource.ResourceDTOResp
     **/
    Boolean findResourceByResourceNameOrCode(ResourceDTOReq resourceDTOReq) throws Exception,BusinessException;

    /**
     * @Description:新增资源
     * @Author: Liuwf
     * @Date:
     * @param resourceDTOReq:
     * @return: void
     **/
    void  saveResource(ResourceDTOReq resourceDTOReq) throws  Exception, BusinessException;

    /**
     * @Description:根据资源id查找资源对象
     * @Author: Liuwf
     * @Date:
     * @param id:
     * @return: com.dj.mall.model.dto.auth.resource.ResourceDTOResp
     **/
    ResourceDTOResp queryResourceById(Integer id) throws Exception,BusinessException;

    /**
     * @Description:资源修改
     * @Author: Liuwf
     * @Date:
     * @param resourceDTOReq:
     * @return: void
     **/
    void updateResource(ResourceDTOReq resourceDTOReq) throws Exception,BusinessException;

    /**
     * @Description:根据id删除
     * @Author: Liuwf
     * @Date:
     * @param id:
     * @return: void
     **/
    void delRoleResourceById(Integer id) throws Exception, BusinessException;

    /**
     * @Description:根据pid查找名字
     * @Author: Liuwf
     * @Date:
     * @param pId:
     * @return: com.dj.mall.model.dto.auth.resource.ResourceDTOResp
     **/
    ResourceDTOResp querNameByPId(Integer pId) throws Exception,BusinessException;


}
