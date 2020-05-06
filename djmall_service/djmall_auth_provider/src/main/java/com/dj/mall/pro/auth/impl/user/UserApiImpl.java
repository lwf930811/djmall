package com.dj.mall.pro.auth.impl.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.api.auth.user.UserApi;
import com.dj.mall.api.cmpt.RedisApi;
import com.dj.mall.bo.auth.user.UserBOReq;
import com.dj.mall.bo.auth.user.UserBOResp;
import com.dj.mall.entity.auth.user.TimeRecordEntity;
import com.dj.mall.entity.auth.user.UserEntity;
import com.dj.mall.entity.auth.user.UserRoleEntity;
import com.dj.mall.mapper.auth.user.UserMapper;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.base.PageResult;
import com.dj.mall.model.constant.SystemConstant;
import com.dj.mall.model.dto.auth.user.UserDTOReq;
import com.dj.mall.model.dto.auth.user.UserDTOResp;
import com.dj.mall.model.dto.auth.user.UserTokenResp;
import com.dj.mall.model.util.*;
import com.dj.mall.model.util.Random;
import com.dj.mall.pro.auth.service.user.TimeRecordApi;
import com.dj.mall.pro.auth.service.user.UserRoleApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserApiImpl extends ServiceImpl<UserMapper, UserEntity> implements UserApi {
    @Autowired
   private UserRoleApi userRoleApi;
    @Autowired
    private TimeRecordApi timeRecordApi;
    @Reference
    private RedisApi redisApi;



    /**
     * @param userName :
     * @Description: 根据userName获取用户实体
     * @Author: Liuwf
     * @Date:
     * @return: com.dj.mall.model.dto.auth.user.UserDTOResp
     **/
    @Override
    public Boolean getUserName(String userName) throws Exception, BusinessException {
        QueryWrapper<UserEntity> userEntityQueryWrapper = new QueryWrapper<>();
        userEntityQueryWrapper.eq("user_name",userName);
        UserEntity userEntity = this.getOne(userEntityQueryWrapper);
        return userEntity == null ? true : false;
    }

    /**
     * @param email :
     * @Description: email
     * @Author: Liuwf
     * @Date:
     * @return: com.dj.mall.model.dto.auth.user.UserDTOResp
     **/
    @Override
    public Boolean getEmail(String email) throws Exception, BusinessException {
        QueryWrapper<UserEntity> userEntityQueryWrapper = new QueryWrapper<>();
        userEntityQueryWrapper.eq("email",email);
        UserEntity userEntity = this.getOne(userEntityQueryWrapper);
        return userEntity == null ? true : false;
    }

    /**
     * @param phone :
     * @Description: phone
     * @Author: Liuwf
     * @Date:
     * @return: com.dj.mall.model.dto.auth.user.UserDTOResp
     **/
    @Override
    public Boolean getPhone(String phone) throws Exception, BusinessException {
        QueryWrapper<UserEntity> userEntityQueryWrapper = new QueryWrapper<>();
        userEntityQueryWrapper.eq("phone",phone);
        UserEntity userEntity = this.getOne(userEntityQueryWrapper);
        return userEntity == null ? true : false;
    }

    /**
     * @param userDTOReq :
     * @Description:注册用户
     * @Author: Liuwf
     * @Date:
     * @return: void
     **/
    @Override
    public void insertUser(UserDTOReq userDTOReq) throws Exception, BusinessException {
//        用户新增
        UserEntity userEntity = DozerUtil.map(userDTOReq, UserEntity.class);
        userEntity.setRegisterTime(LocalDateTime.now());
        userEntity.setStatus(SystemConstant.NOT_ACTIVATE);
        userEntity.setIsDel(SystemConstant.NOT_DELETE_IS_DEL);
        this.save(userEntity);
//        角色资源新增
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUserId(userEntity.getId());
        userRoleEntity.setRoleId(userEntity.getUserRole());
        userRoleEntity.setIsDel(SystemConstant.NOT_DELETE_IS_DEL);
        userRoleApi.save(userRoleEntity);
//        登录时间新增
        TimeRecordEntity timeRecordEntity =  new TimeRecordEntity();
        timeRecordEntity.setUserId(userEntity.getId());
        timeRecordEntity.setUserEndLoginTime(LocalDateTime.now());
        timeRecordApi.save(timeRecordEntity);
    }

    /**
     * @param userName :
     * @Description:获取密码盐
     * @Author: Liuwf
     * @Date:
     * @return: com.dj.mall.model.dto.auth.user.UserDTOResp
     **/
    @Override
    public UserDTOResp getSalt(String userName) throws Exception,BusinessException {
        QueryWrapper<UserEntity> userEntityQueryWrapper = new QueryWrapper<>();
        userEntityQueryWrapper.eq("user_name",userName)
                .or().eq("phone",userName)
                .or().eq("email",userName);
        UserEntity userEntity = this.getOne(userEntityQueryWrapper);
        if(null == userEntity){
            throw new BusinessException(SystemConstant.NULL_USERNAME);
        }
        return DozerUtil.map(userEntity,UserDTOResp.class);
    }
    /**
     * @param userDTOReq :
     * @Description:根据用户名查询对象
     * @Author: Liuwf
     * @Date:
     * @return: com.dj.mall.model.dto.auth.user.UserDTOResp
     **/
    @Override
    public UserDTOResp findUserByNameAndPwd(UserDTOReq userDTOReq) throws Exception, BusinessException {
        QueryWrapper<UserEntity> userEntityQueryWrapper = new QueryWrapper<>();
        userEntityQueryWrapper.or(i -> i.eq("user_name",userDTOReq.getUserName())
                .or().eq("email",userDTOReq.getUserName()).or().eq("phone",userDTOReq.getUserName()));
        userEntityQueryWrapper.eq("password",userDTOReq.getPassword());
        UserEntity userEntity = this.getOne(userEntityQueryWrapper);
        if(null == userEntity){
           throw  new BusinessException(SystemConstant.NUMBER_PWD_ERROR);
        }
        if (!StringUtils.isEmpty(userEntity.getRandomPassword()) &&
                userDTOReq.getPassword().equals(userEntity.getRandomPassword())) {
            throw new BusinessException(SystemConstant.RESET_PASSWORD_CODE,SystemConstant.UPDATE_NEW_PASSWORD);
        }
        if (userEntity.getStatus().equals(SystemConstant.NOT_ACTIVATE_STATUS)) {
            throw new BusinessException(SystemConstant.NOT_ACTIVATE_SUCCESS);
        }
        UpdateWrapper<TimeRecordEntity> updateWrapper = new UpdateWrapper<>();
        if (null != userEntity){
            TimeRecordEntity timeRecordEntity = new TimeRecordEntity();
            timeRecordEntity.setUserEndLoginTime(LocalDateTime.now());
            timeRecordEntity.setUserId(userEntity.getId());
            timeRecordApi.save(timeRecordEntity);
        }
        UserDTOResp resp = DozerUtil.map(userEntity, UserDTOResp.class);
        QueryWrapper<UserRoleEntity> userRoleEntityQueryWrapper = new QueryWrapper<>();
        userRoleEntityQueryWrapper.eq("user_id",userEntity.getId());
        UserRoleEntity userRoleEntity = userRoleApi.getOne(userRoleEntityQueryWrapper);
        resp.setRoleId(userRoleEntity.getRoleId());
//        List<ResourceEntity> resourceEntityList = getBaseMapper().getUserResourceByUserId(resp.getUserId());
//        resp.setPermissionList(DozerUtil.mapList(resourceEntityList, ResourceDTOResp.class));
        return resp;
    }

    /**
     * @param
     * @param userDTOReq :
     * @Description:根据条件查询用户集合
     * @Author: Liuwf
     * @Date:
     * @return: java.util.List<com.dj.mall.model.dto.auth.user.UserDTOResp>
     **/
    @Override
    public PageResult queryUserList( UserDTOReq userDTOReq) throws Exception, BusinessException {
        IPage<UserBOResp> iPage = new Page()
                .setCurrent(userDTOReq.getPageNo())
                .setSize(userDTOReq.getPageSize());
        IPage<UserBOResp> pageInfo = getBaseMapper().queryUserList(iPage, DozerUtil.map(userDTOReq, UserBOReq.class));
        return PageResult.builder().pages(pageInfo.getPages()).list(DozerUtil.mapList(pageInfo.getRecords(),UserDTOResp.class)).build();
    }

    /**
     * @param id :
     * @Description:根据id查找用户
     * @Author: Liuwf
     * @Date:
     * @return: com.dj.mall.model.dto.auth.user.UserDTOResp
     **/
    @Override
    public UserDTOResp queryUserById(Integer id) throws Exception, BusinessException {
        QueryWrapper<UserEntity> userEntityQueryWrapper = new QueryWrapper<>();
        userEntityQueryWrapper.eq("id",id);
        return DozerUtil.map(this.getOne(userEntityQueryWrapper),UserDTOResp.class);
    }

    /**
     * @param userDTOReq :
     * @Description:修改用户
     * @Author: Liuwf
     * @Date:
     * @return: void
     **/
    @Override
    public void updateUser(UserDTOReq userDTOReq) throws Exception, BusinessException {
        UpdateWrapper<UserEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("user_name", userDTOReq.getUserName())
                .set("phone", userDTOReq.getPhone())
                .set("email", userDTOReq.getEmail())
                .set("user_sex", userDTOReq.getUserSex());
        updateWrapper.eq("id", userDTOReq.getUserId());
         this.update(updateWrapper);
    }

    /**
     * @param ids :
     * @Description:根据用户ID删除
     * @Author: Liuwf
     * @Date:
     * @return: void
     **/
    @Override
    public void delUserRoleByIds(Integer[] ids) throws Exception, BusinessException {
        UpdateWrapper<UserEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("is_del", SystemConstant.DELETE_IS_DEL);
        updateWrapper.in("id", ids);
        this.update(updateWrapper);
        UpdateWrapper<UserRoleEntity> userRoleEntityUpdateWrapper = new UpdateWrapper<>();
        userRoleEntityUpdateWrapper.set("is_del", SystemConstant.DELETE_IS_DEL);
        userRoleEntityUpdateWrapper.in("user_id", ids);
        userRoleApi.update(userRoleEntityUpdateWrapper);
    }

    /**
     * @param id         :
     * @param userStatus :
     * @Description:修改激活状态
     * @Author: Liuwf
     * @Date:
     * @return: void
     **/
    @Override
    public void updateStatus(Integer id, Integer userStatus) throws Exception, BusinessException {
        UpdateWrapper<UserEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", userStatus);
        updateWrapper.eq("id",id);
        this.update(updateWrapper);

    }

    /**
     * @param userId   :
     * @param userRole :
     * @Description:修改用户角色
     * @Author: Liuwf
     * @Date:
     * @return: void
     **/
    @Override
    public void updateUserRole(Integer userId, Integer userRole) throws Exception, BusinessException {
        UpdateWrapper<UserEntity> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.set("user_role",userRole);
        userUpdateWrapper.eq("id",userId);
        this.update(userUpdateWrapper);
        UpdateWrapper<UserRoleEntity> userRoleUpdateWrapper = new UpdateWrapper<>();
        userRoleUpdateWrapper.set("role_id",userRole);
        userRoleUpdateWrapper.eq("user_id",userId);
        userRoleApi.update(userRoleUpdateWrapper);
    }

    /***
     * @Description:获取验证码
     * @Author: Liuwf
     * @Date:
     * @param phone :
     * @return: com.dj.mall.model.dto.auth.user.UserDTOResp
     **/
    @Override
    public UserDTOResp getCode(String phone) throws Exception, BusinessException {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        UserEntity userEntity = this.getOne(queryWrapper);
        //获取手机短信验证码
        int newcode = MessageVerifyUtils.getNewcode();
        userEntity.setVerify(String.valueOf(newcode));
        MessageVerifyUtils.sendSms(String.valueOf(userEntity.getPhone()), String.valueOf(newcode));
        //根据手机号修改验证码
        UpdateWrapper<UserEntity> userEntityUpdateWrapper = new UpdateWrapper<>();
        userEntityUpdateWrapper.set("verify",String.valueOf(newcode));
        userEntityUpdateWrapper.eq("phone", phone);
        this.update(userEntityUpdateWrapper);
        return DozerUtil.map(userEntity, UserDTOResp.class);
    }

    /**
     * @param userDTOReq :
     * @Description:手机验证码修改密码
     * @Author: Liuwf
     * @Date:
     * @return: com.dj.mall.model.dto.auth.user.UserDTOResp
     **/
    @Override
    public void updatePwdByCode(UserDTOReq userDTOReq) throws Exception, BusinessException {
        UpdateWrapper<UserEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("password", userDTOReq.getPassword());
        updateWrapper.set("verify", null);
        updateWrapper.eq("phone", userDTOReq.getPhone());
        updateWrapper.eq("verify", userDTOReq.getVerify());
        this.update(updateWrapper);
        //发送邮件
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", userDTOReq.getPhone());
        UserEntity userEntity = this.getOne(queryWrapper);
        //可以精确到时分秒
        DateFormat df = DateFormat.getDateTimeInstance();
        JavaEmailUtils.sendEmail(userEntity.getEmail(), "修改密码", "您的账户"+userEntity.getNickName()+",于"+df.format(new Date())+"时进行密码修改成功。");
    }

    /**
     * @param id :
     * @Description:重置密码
     * @Author: Liuwf
     * @Date:
     * @return: void
     **/
    @Override
    public void updateUserPwd(Integer id) throws Exception, BusinessException {
        Integer randomNum = Random.getRandNum();
        String salt = PasswordSecurityUtil.generateSalt();
        String mdPassword = PasswordSecurityUtil.enCode32(randomNum.toString());
        String saltPassword = PasswordSecurityUtil.enCode32(salt + mdPassword);
        UpdateWrapper<UserEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("password",saltPassword)
                .set("random_password",saltPassword)
                .set("salt",salt);
         updateWrapper.eq("id", id);
         this.update(updateWrapper);
//         发送邮件
        UserEntity userEntity = this.getById(id);
        DateFormat df = DateFormat.getDateTimeInstance();
        JavaEmailUtils.sendEmail(userEntity.getEmail(), "重置密码",
                "尊敬的"+userEntity.getNickName()+",您的密码已被管理员于"+df.format(new Date())+"时重置为"+randomNum+",为了您的账户安全，请及时修改。</br>" +
                        "<a href='http://localhost:8081/admin/auth/user/toLogin'>点我去登陆</a><br>"
        );


    }

    /**
     * @param userDTOReq :
     * @Description:修改密码
     * @Author: Liuwf
     * @Date:
     * @return: void
     **/
    @Override
    public void updatePasswordByUserName(UserDTOReq userDTOReq) throws Exception, BusinessException {
        UpdateWrapper<UserEntity> userEntityUpdateWrapper = new UpdateWrapper<>();
        userEntityUpdateWrapper.set("salt",userDTOReq.getSalt())
                .set("password",userDTOReq.getPassword());
        userEntityUpdateWrapper.eq("user_name",userDTOReq.getUserName());
        this.update(userEntityUpdateWrapper);

    }

    /**
     * @param userName :
     * @param password :
     * @Description:token普通登陆
     * @Author: Liuwf
     * @Date:
     * @return: com.dj.mall.model.dto.auth.user.UserDTOResp
     **/
    @Override
    public UserTokenResp tokenLoginByNameAndPwd(String userName, String password) throws Exception, BusinessException {
        QueryWrapper<UserEntity> userEntityQueryWrapper = new QueryWrapper<>();
        userEntityQueryWrapper.or(i -> i.eq("user_name",userName)
                .or().eq("email",userName).or().eq("phone",userName));
        userEntityQueryWrapper.eq("password",password);
        userEntityQueryWrapper.eq("is_del",SystemConstant.NOT_DELETE_IS_DEL);
        UserEntity userEntity = this.getOne(userEntityQueryWrapper);
        if(null == userEntity){
            throw  new BusinessException(SystemConstant.NUMBER_PWD_ERROR);
        }
        QueryWrapper<UserRoleEntity> userRoleEntityQueryWrapper = new QueryWrapper<>();
        userRoleEntityQueryWrapper.eq("user_id",userEntity.getId());
        UserRoleEntity userRoleEntity = userRoleApi.getOne(userRoleEntityQueryWrapper);
        if (!userRoleEntity.equals(SystemConstant.USER_ROLE_ID)){
            throw  new BusinessException(SystemConstant.NOT_PERMISSION);
        }
        TimeRecordEntity timeRecordEntity = new TimeRecordEntity();
        timeRecordEntity.setUserEndLoginTime(LocalDateTime.now());
        timeRecordEntity.setUserId(userEntity.getId());
        timeRecordApi.save(timeRecordEntity);
        String token = UUID.randomUUID().toString().replace("-", "");
        redisApi.set(SystemConstant.USER_TOKEN + token,DozerUtil.map(userEntity,UserDTOResp.class),22 * 24 * 60 *60);
        UserTokenResp userTokenResp = new UserTokenResp();
        userTokenResp.setNickName(userEntity.getNickName());
        userTokenResp.setToken(token);
        userTokenResp.setUserId(userEntity.getId());
        userTokenResp.setUserName(userTokenResp.getUserName());
        return userTokenResp;
    }

    /**
     * @param phone  :
     * @param verify :
     * @Description:token手机登陆
     * @Author: Liuwf
     * @Date:
     * @return: com.dj.mall.model.dto.auth.user.UserDTOResp
     **/
    @Override
    public UserTokenResp tokenLoginByPhoneAndVerify(String phone, String verify) throws Exception, BusinessException {
        QueryWrapper<UserEntity> userEntityQueryWrapper = new QueryWrapper<>();
        userEntityQueryWrapper.eq("phone",phone);
        userEntityQueryWrapper.eq("verify",verify);
        userEntityQueryWrapper.eq("is_del",SystemConstant.NOT_DELETE_IS_DEL);
        UserEntity userEntity = this.getOne(userEntityQueryWrapper);
        if(null == userEntity){
            throw  new BusinessException(SystemConstant.NUMBER_PWD_ERROR);
        }
        QueryWrapper<UserRoleEntity> userRoleEntityQueryWrapper = new QueryWrapper<>();
        userRoleEntityQueryWrapper.eq("user_id",userEntity.getId());
        UserRoleEntity userRoleEntity = userRoleApi.getOne(userRoleEntityQueryWrapper);
        if (!userRoleEntity.equals(SystemConstant.USER_ROLE_ID)){
            throw  new BusinessException(SystemConstant.NOT_PERMISSION);
        }
        TimeRecordEntity timeRecordEntity = new TimeRecordEntity();
        timeRecordEntity.setUserEndLoginTime(LocalDateTime.now());
        timeRecordEntity.setUserId(userEntity.getId());
        timeRecordApi.save(timeRecordEntity);
        String token = UUID.randomUUID().toString().replace("-", "");
        redisApi.set(SystemConstant.USER_TOKEN + token,DozerUtil.map(userEntity,UserDTOResp.class),22 * 24 * 60 *60);
        UserTokenResp userTokenResp = new UserTokenResp();
        userTokenResp.setNickName(userEntity.getNickName());
        userTokenResp.setToken(token);
        userTokenResp.setUserId(userEntity.getId());
        userTokenResp.setUserName(userTokenResp.getUserName());
        return userTokenResp;
    }


}
