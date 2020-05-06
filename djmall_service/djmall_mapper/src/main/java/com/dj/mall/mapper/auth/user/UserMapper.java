package com.dj.mall.mapper.auth.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dj.mall.bo.auth.user.UserBOReq;
import com.dj.mall.bo.auth.user.UserBOResp;
import com.dj.mall.entity.auth.resource.ResourceEntity;
import com.dj.mall.entity.auth.user.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @Description:用户mapper
 * @Author: Liuwf
 * @Date:
 * @return: null
 **/
public interface UserMapper extends BaseMapper<UserEntity> {
    /**
     * @Description:根据用户id查找资源
     * @Author: Liuwf
     * @Date:
     * @param userId:
     * @return: java.util.List<com.dj.mall.entity.auth.resource.ResourceEntity>
     **/
    List<ResourceEntity> getUserResourceByUserId(Integer userId) throws DataAccessException;

    /**
     * @Description:用户list
     * @Author: Liuwf
     * @Date:
     * @param page
     * @param userBOReq :
     * @return: java.util.List<com.dj.mall.entity.auth.user.UserEntity>
     **/
   IPage<UserBOResp> queryUserList(IPage page, @Param("user") UserBOReq userBOReq) throws DataAccessException;
}
