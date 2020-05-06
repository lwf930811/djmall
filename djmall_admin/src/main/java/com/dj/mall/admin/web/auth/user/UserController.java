package com.dj.mall.admin.web.auth.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.mall.admin.vo.auth.resource.ResourceVoResp;
import com.dj.mall.admin.vo.auth.user.UserVOReq;
import com.dj.mall.admin.vo.auth.user.UserVOResp;
import com.dj.mall.api.auth.user.UserApi;
import com.dj.mall.api.cmpt.RedisApi;
import com.dj.mall.bo.auth.user.UserBOReq;
import com.dj.mall.bo.auth.user.UserBOResp;
import com.dj.mall.entity.auth.user.UserEntity;
import com.dj.mall.model.base.PageResult;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.constant.PermissionsCode;
import com.dj.mall.model.constant.RedisConstant;
import com.dj.mall.model.constant.SystemConstant;
import com.dj.mall.model.dto.auth.resource.ResourceDTOResp;
import com.dj.mall.model.dto.auth.user.UserDTOReq;
import com.dj.mall.model.dto.auth.user.UserDTOResp;
import com.dj.mall.model.util.DozerUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.admin.web.auth.user
 * @ClassName: UserController
 * @Author: Liuwf
 * @Description: 用户
 * @Date: 2020/3/24 19:13
 * @Version: 1.0
 */
@RestController
@RequestMapping("/auth/user/")
public class UserController {
    @Reference
    private UserApi userApi;
    @Reference
    private RedisApi redisApi;
   /**
     * @Description:根据userName查找对象
     * @Author: Liuwf
     * @Date:
     * @param userName:
     * @return: boolean
     **/
    @GetMapping("findName")
    public boolean getUserName(String userName) throws Exception{
        Boolean userName1 = userApi.getUserName(userName);
        return userName1;
    }
    /**
     * @Description:根据email查找对象
     * @Author: Liuwf
     * @Date:
     * @param email:
     * @return: boolean
     **/
    @GetMapping("findEmail")
    public boolean getEmail(String email) throws Exception{
        Boolean email1 = userApi.getEmail(email);
        return email1;
    }
    /**
     * @Description:根据phone查找对象
     * @Author: Liuwf
     * @Date:
     * @param phone:
     * @return: boolean
     **/
    @GetMapping("findPhone")
    public boolean getPhone(String phone) throws Exception{
        Boolean phone1 = userApi.getPhone(phone);
        return phone1;
    }

    /**
     * @Description:注册用户
     * @Author: Liuwf
     * @Date:
     * @param userVOReq:
     * @return: com.dj.mall.model.base.ResultModel<java.lang.Object>
     **/
    @PostMapping("insertUser")
    public ResultModel<Object> addUser(UserVOReq userVOReq) throws Exception{
        userApi.insertUser(DozerUtil.map(userVOReq, UserDTOReq.class));
        return new ResultModel<>().success(true);
    }

    /**
     * @Description:获取密码盐
     * @Author: Liuwf
     * @Date:
     * @param userName:
     * @return: com.dj.mall.model.base.ResultModel<java.lang.Object>
     **/
    @GetMapping("getSalt")
    public ResultModel<Object> getSalt(String userName) throws Exception{
        Assert.hasText(userName,SystemConstant.NOT_NULL);
        UserVOResp userVOResp = DozerUtil.map(userApi.getSalt(userName), UserVOResp.class);
        ResultModel resultModel = new ResultModel().success(true);
        resultModel.setData(userVOResp.getSalt());
        return resultModel;
    }

    /**
     * @Description:登录
     * @Author: Liuwf
     * @Date:
     * @param UserVOReq:
     * @param session:
     * @return: com.dj.mall.model.base.ResultModel
     **/
    @GetMapping("login")
    public ResultModel login(UserVOReq UserVOReq, HttpSession session) throws Exception {
        Assert.hasText(UserVOReq.getUserName(),SystemConstant.NOT_NULL);
        Assert.hasText(UserVOReq.getPassword(), SystemConstant.NOT_NULL);
        UserDTOResp userDTOResp = userApi.findUserByNameAndPwd(DozerUtil.map(UserVOReq,UserDTOReq.class));
        session.setAttribute(SystemConstant.USER_SESSION,userDTOResp);
        //shiro 登录方式
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(UserVOReq.getUserName(), UserVOReq.getPassword());
        subject.login(token);
        return new ResultModel<>().success();
    }
    /**
     * @Description:获取用户资源菜单
     * @Author: Liuwf
     * @Date:
     * @param session:
     * @return: com.dj.mall.model.base.ResultModel
     **/
    @GetMapping("getMenu")
    public ResultModel getMenu(HttpSession session) throws Exception {
        UserDTOResp userDTOResp = (UserDTOResp) session.getAttribute(SystemConstant.USER_SESSION);
//        List<ResourceDTOResp> permissionList = userDTOResp.getPermissionList();
        List<ResourceDTOResp> permissionList = redisApi.getHash(RedisConstant.ROLE_RESOURCE_ALL, RedisConstant.ROLE_RESOURCE + userDTOResp.getRoleId());
        ArrayList<ResourceDTOResp> menuList = new ArrayList<>();
        for (ResourceDTOResp resourceDTOResp : permissionList) {
            if(resourceDTOResp.getResourceType().equals(SystemConstant.RESOURCE_TYPE)){
                menuList.add(resourceDTOResp);
            }
        }
        return new ResultModel<>().success(DozerUtil.mapList(menuList, ResourceVoResp.class));
    }

    /**
     * @Description:用户list
     * @Author: Liuwf
     * @Date:
     * @param userVOReq:
     * @return: com.dj.mall.model.base.ResultModel
     **/
    @GetMapping("userList")
    @RequiresPermissions(value = PermissionsCode.USER_MANAGE)
    public ResultModel getUserList(UserVOReq userVOReq) throws Exception {
        PageResult pageResult = userApi.queryUserList(DozerUtil.map(userVOReq, UserDTOReq.class));
        return new ResultModel<>().success(PageResult.builder().list(DozerUtil.mapList(pageResult.getList(),UserVOResp.class)).pages(pageResult.getPages()).build());
    }

    /**
     * @Description:用户修改
     * @Author: Liuwf
     * @Date:
     * @param userVOReq:
     * @return: com.dj.mall.model.base.ResultModel
     **/
    @RequestMapping("update")
    @RequiresPermissions(value = PermissionsCode.USER_UPDATE_BTN)
    public ResultModel updateUser(UserVOReq userVOReq) throws Exception {
        userApi.updateUser(DozerUtil.map(userVOReq, UserDTOReq.class));
        return new ResultModel<>().success();
    }

    /**
     * @Description:用户删除
     * @Author: Liuwf
     * @Date:
     * @param ids:
     * @return: com.dj.mall.model.base.ResultModel
     **/
    @RequestMapping("delete")
    @RequiresPermissions(value = PermissionsCode.USER_DELETE_BTN)
    public ResultModel deleteUser(Integer[] ids) throws Exception {
        userApi.delUserRoleByIds(ids);
        return new ResultModel<>().success();
    }

    /**
     * @Description:用户状态修改
     * @Author: Liuwf
     * @Date:
     * @param id:
     * @param userStatus:
     * @return: com.dj.mall.model.base.ResultModel
     **/
    @RequestMapping("updateStatus")
    @RequiresPermissions(value = PermissionsCode.USER_ACTIVE_BTN)
    public ResultModel updateStatus(Integer id, Integer userStatus) throws Exception {
        userApi.updateStatus(id,userStatus);
        return new ResultModel<>().success();
    }

    /**
     * @Description:用户角色授权
     * @Author: Liuwf
     * @Date:
     * @param userId:
     * @param userRole:
     * @return: com.dj.mall.model.base.ResultModel
     **/
    @RequestMapping("updateRole")
    @RequiresPermissions(value = PermissionsCode.USER_AUTH_BTN)
    public ResultModel updateRole(Integer userId,Integer userRole) throws Exception {
        userApi.updateUserRole(userId,userRole);
        return new ResultModel<>().success();
    }

    /**
     * @Description:发送短信验证码
     * @Author: Liuwf
     * @Date:
     * @param phone:
     * @return: com.dj.mall.model.base.ResultModel
     **/
    @RequestMapping("sendMessage")
    public ResultModel sendMessage(String phone) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        UserDTOResp userDTOResp = userApi.getCode(phone);
        if (null == userDTOResp){
            return new ResultModel<>().error(SystemConstant.PHONE_NOT_REGISTER);
        }
        resultMap.put("ver", String.valueOf(userDTOResp.getVerify()));
        resultMap.put("msg", SystemConstant.SEND_SUCCESS);
        return new ResultModel<>().success(resultMap);
    }

    /**
     * @Description:找回密码
     * @Author: Liuwf
     * @Date:
     * @param userVOReq:
     * @return: com.dj.mall.model.base.ResultModel<java.lang.Object>
     **/
    @RequestMapping("findPhonePassword")
    public ResultModel<Object> findPassword(UserVOReq userVOReq) throws Exception {
        userApi.updatePwdByCode(DozerUtil.map(userVOReq,UserDTOReq.class));
        return new ResultModel<>().success();
    }

    /**
     * @Description:重置密码
     * @Author: Liuwf
     * @Date:
     * @param id:
     * @return: com.dj.mall.model.base.ResultModel<java.lang.Object>
     **/
    @RequestMapping("resetPassword")
    @RequiresPermissions(value = PermissionsCode.USER_RESERT_PASSWORD_BTN)
    public ResultModel<Object> resetPassword(Integer id) throws Exception {
        userApi.updateUserPwd(id);
        return new ResultModel<>().success();
    }

    /**
     * @Description:修改密码
     * @Author: Liuwf
     * @Date:
     * @param userVOReq:
     * @return: com.dj.mall.model.base.ResultModel<java.lang.Object>
     **/
    @RequestMapping("updatePassword")
    public ResultModel<Object> updatePassword(UserVOReq userVOReq) throws Exception {
        userApi.updatePasswordByUserName(DozerUtil.map(userVOReq,UserDTOReq.class));
        return new ResultModel<>().success();
    }

}
