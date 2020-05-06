package com.dj.mall.pro.auth.impl.resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.api.auth.resource.ResourceApi;
import com.dj.mall.entity.auth.resource.ResourceEntity;
import com.dj.mall.entity.auth.role.RoleResourceEntity;
import com.dj.mall.mapper.auth.resource.ResourceMapper;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.constant.SystemConstant;
import com.dj.mall.model.dto.auth.resource.ResourceDTOReq;
import com.dj.mall.model.dto.auth.resource.ResourceDTOResp;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.pro.auth.service.role.RoleResourceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.pro.auth.impl.resource
 * @ClassName: ResourceApiImpl
 * @Author: Liuwf
 * @Description: 资源实现类
 * @Date: 2020/3/28 18:29
 * @Version: 1.0
 */
@Service
public class ResourceApiImpl extends ServiceImpl<ResourceMapper,ResourceEntity>implements ResourceApi {
    @Autowired
    private RoleResourceApi roleResourceApi;


    /**
     * @Description:查找资源
     * @Author: Liuwf
     * @Date:
     * @return: java.util.List<com.dj.mall.model.dto.auth.resource.ResourceDTOresp>
     **/
    @Override
    public List<ResourceDTOResp> queryResourceList() throws Exception, BusinessException {
        QueryWrapper<ResourceEntity> resourceQueryWrapper = new QueryWrapper<>();
        resourceQueryWrapper.eq("is_del", SystemConstant.NOT_DELETE_IS_DEL);
        List<ResourceEntity> list = this.list(resourceQueryWrapper);
        return DozerUtil.mapList(list,ResourceDTOResp.class);
    }
    /**
     * @param resourceDTOReq :
     * @Description:根据资源名或者编码去重
     * @Author: Liuwf
     * @Date:
     * @return: com.dj.mall.model.dto.auth.resource.ResourceDTOResp
     **/
    @Override
    public Boolean findResourceByResourceNameOrCode(ResourceDTOReq resourceDTOReq) throws Exception, BusinessException {
        QueryWrapper<ResourceEntity> resourceEntityQueryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(resourceDTOReq.getResourceName())){
            resourceEntityQueryWrapper.eq("resource_name",resourceDTOReq.getResourceName());
        }
        if(!StringUtils.isEmpty(resourceDTOReq.getResourceCode())){
            resourceEntityQueryWrapper.eq("resource_code",resourceDTOReq.getResourceCode());
        }
        resourceEntityQueryWrapper.eq("is_del",SystemConstant.NOT_DELETE_IS_DEL);
        ResourceEntity resourceEntity = this.getOne(resourceEntityQueryWrapper);
        return resourceEntity == null ? true : false;
    }

    /**
     * @param resourceDTOReq :
     * @Description:新增资源
     * @Author: Liuwf
     * @Date:
     * @return: void
     **/
    @Override
    public void saveResource(ResourceDTOReq resourceDTOReq) throws Exception, BusinessException {
        resourceDTOReq.setResourceCode(resourceDTOReq.getResourceCode().toUpperCase());
        resourceDTOReq.setIsDel(SystemConstant.NOT_DELETE_IS_DEL);
        this.save(DozerUtil.map(resourceDTOReq,ResourceEntity.class));
    }

    /**
     * @param id :
     * @Description:根据资源id查找资源对象
     * @Author: Liuwf
     * @Date:
     * @return: com.dj.mall.model.dto.auth.resource.ResourceDTOResp
     **/
    @Override
    public ResourceDTOResp queryResourceById(Integer id) throws Exception, BusinessException {
        QueryWrapper<ResourceEntity> resourceEntityQueryWrapper = new QueryWrapper<>();
        resourceEntityQueryWrapper.eq("id",id);
        resourceEntityQueryWrapper.eq("is_del",SystemConstant.NOT_DELETE_IS_DEL);
        return DozerUtil.map(this.getOne(resourceEntityQueryWrapper),ResourceDTOResp.class);
    }

    /**
     * @param resourceDTOReq :
     * @Description:资源修改
     * @Author: Liuwf
     * @Date:
     * @return: void
     **/
    @Override
    public void updateResource(ResourceDTOReq resourceDTOReq) throws Exception, BusinessException {
        UpdateWrapper<ResourceEntity> resourceEntityUpdateWrapper = new UpdateWrapper<>();
        resourceEntityUpdateWrapper.set("resource_name",resourceDTOReq.getResourceName());
        resourceEntityUpdateWrapper.set("url",resourceDTOReq.getUrl());
        resourceEntityUpdateWrapper.set("resource_type",resourceDTOReq.getResourceType());
        resourceEntityUpdateWrapper.eq("id",resourceDTOReq.getResourceId());
        this.update(resourceEntityUpdateWrapper);
    }


    /**
     * @param id :
     * @Description:根据id删除
     * @Author: Liuwf
     * @Date:
     * @return: void
     **/
    @Override
    public void delRoleResourceById(Integer id) throws Exception, BusinessException {
        ArrayList<Integer> ids = new ArrayList<>();
        getIds(id,ids);
        ids.add(id);
//        资源删除
        UpdateWrapper<ResourceEntity> entityUpdateWrapper = new UpdateWrapper<>();
        entityUpdateWrapper.set("is_del",SystemConstant.DELETE_IS_DEL);
        entityUpdateWrapper.in("id",id);
        this.update(entityUpdateWrapper);
//        角色资源删除
        UpdateWrapper<RoleResourceEntity> roleResourceEntityUpdateWrapper = new UpdateWrapper<>();
        roleResourceEntityUpdateWrapper.set("is_del",SystemConstant.DELETE_IS_DEL);
        roleResourceEntityUpdateWrapper.in("id",id);
        roleResourceApi.update(roleResourceEntityUpdateWrapper);
    }
    public void getIds(Integer id, List<Integer> ids) throws Exception{
        UpdateWrapper<ResourceEntity> entityUpdateWrapper = new UpdateWrapper<>();
        entityUpdateWrapper.set("is_del",SystemConstant.DELETE_IS_DEL);
        entityUpdateWrapper.eq("p_id",id);
        List<ResourceEntity> resourceEntityList = this.list(entityUpdateWrapper);
        if (null != resourceEntityList && resourceEntityList.size() >0){
            for (ResourceEntity resourceEntity : resourceEntityList) {
                ids.add(resourceEntity.getId());
                getIds(id,ids);
            }
        }


    }
    /**
     * @param pId :
     * @Description:根据pid查找名字
     * @Author: Liuwf
     * @Date:
     * @return: com.dj.mall.model.dto.auth.resource.ResourceDTOResp
     **/
    @Override
    public ResourceDTOResp querNameByPId(Integer pId) throws Exception, BusinessException {
        QueryWrapper<ResourceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",pId);
        return DozerUtil.map(this.getOne(queryWrapper), ResourceDTOResp.class);
    }
}
