package com.dj.mall.api.auth.user;

import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.base.PageResult;
import com.dj.mall.model.dto.auth.user.UserDTOReq;
import com.dj.mall.model.dto.auth.user.UserDTOResp;
import com.dj.mall.model.dto.auth.user.UserTokenResp;


public interface UserApi {
    /**
     * @Description: 根据userName获取用户实体
     * @Author: Liuwf
     * @Date:
     * @param userName:
     * @return: com.dj.mall.model.dto.auth.user.UserDTOResp
     **/
    Boolean getUserName(String userName) throws  Exception, BusinessException;

    /**
     * @Description: email
     * @Author: Liuwf
     * @Date:
     * @param email:
     * @return: com.dj.mall.model.dto.auth.user.UserDTOResp
     **/
    Boolean getEmail(String email) throws  Exception, BusinessException;

    /**
     * @Description: phone
     * @Author: Liuwf
     * @Date:
     * @param phone:
     * @return: com.dj.mall.model.dto.auth.user.UserDTOResp
     **/
    Boolean getPhone(String phone) throws  Exception, BusinessException;

    /**
     * @Description:注册用户
     * @Author: Liuwf
     * @Date:
     * @param userDTOReq:
     * @return: void
     **/
    void insertUser(UserDTOReq userDTOReq) throws Exception, BusinessException;

    /**
     * @Description:获取密码盐
     * @Author: Liuwf
     * @Date:
     * @param userName:
     * @return: com.dj.mall.model.dto.auth.user.UserDTOResp
     **/
    UserDTOResp getSalt(String userName) throws  Exception, BusinessException;

    /**
     * @Description:根据用户名查询对象
     * @Author: Liuwf
     * @Date:
     * @param  userDTOReq:
     * @return: com.dj.mall.model.dto.auth.user.UserDTOResp
     **/
    UserDTOResp findUserByNameAndPwd(UserDTOReq userDTOReq) throws Exception, BusinessException;

    /**
     * @Description:根据条件查询用户集合
     * @Author: Liuwf
     * @Date:
     * @param userDTOReq:
     * @return: java.util.List<com.dj.mall.model.dto.auth.user.UserDTOResp>
     **/
   PageResult queryUserList( UserDTOReq userDTOReq) throws Exception,BusinessException;

    /**
     * @Description:根据id查找用户
     * @Author: Liuwf
     * @Date:
     * @param id:
     * @return: com.dj.mall.model.dto.auth.user.UserDTOResp
     **/
    UserDTOResp queryUserById(Integer id) throws  Exception,BusinessException;

    /**
     * @Description:修改用户
     * @Author: Liuwf
     * @Date:
     * @param userDTOReq:
     * @return: void
     **/
    void  updateUser(UserDTOReq userDTOReq) throws Exception, BusinessException;

    /**
     * @Description:根据用户ID删除
     * @Author: Liuwf
     * @Date:
     * @param ids:
     * @return: void
     **/
    void delUserRoleByIds(Integer[] ids) throws  Exception, BusinessException;

    /**
     * @Description:修改激活状态
     * @Author: Liuwf
     * @Date:
     * @param id:
     * @param userStatus:
     * @return: void
     **/
    void updateStatus(Integer id, Integer userStatus) throws Exception, BusinessException;

    /**
     * @Description:修改用户角色
     * @Author: Liuwf
     * @Date:
     * @param userId:
     * @param userRole:
     * @return: void
     **/
    void updateUserRole(Integer userId,Integer userRole) throws Exception, BusinessException;

    /***
     * @Description:获取验证码
     * @Author: Liuwf
     * @Date:
     * @param phone:
     * @return: com.dj.mall.model.dto.auth.user.UserDTOResp
     **/
    UserDTOResp getCode(String phone) throws Exception, BusinessException;

    /**
     * @Description:手机验证码修改密码
     * @Author: Liuwf
     * @Date:
     * @param userDTOReq:
     * @return: com.dj.mall.model.dto.auth.user.UserDTOResp
     **/
    void updatePwdByCode(UserDTOReq userDTOReq) throws Exception,BusinessException;

    /**
     * @Description:重置密码
     * @Author: Liuwf
     * @Date:
     * @param id:
     * @return: void
     **/
    void updateUserPwd(Integer id) throws  Exception, BusinessException;

    /**
     * @Description:修改密码
     * @Author: Liuwf
     * @Date:
     * @param userDTOReq:
     * @return: void
     **/
    void updatePasswordByUserName(UserDTOReq userDTOReq) throws Exception,BusinessException;

    /**
     * @Description:token普通登陆
     * @Author: Liuwf
     * @Date:
     * @param userName :
     * @param password :
     * @return: com.dj.mall.model.dto.auth.user.UserDTOResp
     **/
    UserTokenResp tokenLoginByNameAndPwd(String userName, String password) throws Exception,BusinessException;

    /**
     * @Description:token手机登陆
     * @Author: Liuwf
     * @Date:
     * @param phone:
     * @param verify:
     * @return: com.dj.mall.model.dto.auth.user.UserDTOResp
     **/
    UserTokenResp tokenLoginByPhoneAndVerify(String phone, String verify) throws Exception,BusinessException;


}
