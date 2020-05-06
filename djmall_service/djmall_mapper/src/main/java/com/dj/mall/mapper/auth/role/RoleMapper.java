package com.dj.mall.mapper.auth.role;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.mall.entity.auth.resource.ResourceEntity;
import com.dj.mall.entity.auth.role.RoleEntity;
import com.dj.mall.model.dto.auth.resource.ResourceDTOResp;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.mapper.auth.role
 * @ClassName: RoleMapper
 * @Author: Liuwf
 * @Description: 角色mapper
 * @Date: 2020/3/26 22:29
 * @Version: 1.0
 */
public interface RoleMapper extends BaseMapper<RoleEntity> {


    List<ResourceEntity> getRoleResource(Integer roleId)throws DataAccessException;


}
