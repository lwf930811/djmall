package com.dj.mall.admin.web.auth.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.auth.role.RoleVOResp;
import com.dj.mall.admin.vo.auth.user.UserVOResp;
import com.dj.mall.admin.vo.dict.dict.DictVOResp;
import com.dj.mall.api.auth.role.RoleApi;
import com.dj.mall.api.auth.user.UserApi;
import com.dj.mall.api.dict.dict.DictApi;
import com.dj.mall.model.constant.PermissionsCode;
import com.dj.mall.model.constant.SystemConstant;
import com.dj.mall.model.dto.auth.role.RoleDTOResp;
import com.dj.mall.model.dto.auth.user.UserDTOResp;

import com.dj.mall.model.dto.dict.dict.DictDTOResp;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.model.util.PasswordSecurityUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.admin.web.auth.user
 * @ClassName: UserPageController
 * @Author: Liuwf
 * @Description: 用户视图
 * @Date: 2020/3/26 21:24
 * @Version: 1.0
 */
@Controller
@RequestMapping("/auth/user/")
public class UserPageController {
    @Reference
    private RoleApi roleApi;
    @Reference
    private UserApi userApi;
    @Reference
    private DictApi dictApi;

    /**
     * @Description:去登陆页面
     * @Author: Liuwf
     * @Date:

     * @return: java.lang.String
     **/
    @RequestMapping("toLogin")
    public String toLogin(){
        return "auth/user/login";
    }

    /**
     * @Description:去登陆页面
     * @Author: Liuwf
     * @Date:
     * @return: java.lang.String
     **/
    @RequestMapping("toRegister")
    public String toRegister(Model model) throws Exception {
        List<RoleDTOResp> roleDTOResps = roleApi.queryRoleList();
        model.addAttribute("role", DozerUtil.mapList(roleDTOResps, RoleVOResp.class));
        model.addAttribute("salt", PasswordSecurityUtil.generateSalt());
        List<DictDTOResp> dictDTOResps = dictApi.queryDictNameByPcode(SystemConstant.P_CODE_SEX);
        model.addAttribute("sexList", DozerUtil.mapList(dictDTOResps, DictVOResp.class));
        return "auth/user/register";
    }

    /**
     * @Description:用户展示页面
     * @Author: Liuwf
     * @Date:
     * @param model:
     * @return: java.lang.String
     **/
    @RequestMapping("toList")
    @RequiresPermissions(value = PermissionsCode.USER_MANAGE)
    public String toShow(Model model) throws Exception {
        List<RoleDTOResp> roleList = roleApi.queryRoleList();
        model.addAttribute("role", DozerUtil.mapList(roleList, RoleVOResp.class));
        List<DictDTOResp> dictDTOResps = dictApi.queryDictNameByPcode(SystemConstant.P_CODE_STATUS);
        model.addAttribute("statusList", DozerUtil.mapList(dictDTOResps, DictVOResp.class));
        List<DictDTOResp> dictDTORespsList = dictApi.queryDictNameByPcode(SystemConstant.P_CODE_SEX);
        model.addAttribute("sexList", DozerUtil.mapList(dictDTORespsList, DictVOResp.class));
        return "auth/user/user_list";
    }
    /**
     * @Description:修改
     * @Author: Liuwf
     * @Date:
     * @param model:
     * @param id:
     * @return: java.lang.String
     **/
    @RequestMapping("toUpdate")
    @RequiresPermissions(value = PermissionsCode.USER_UPDATE_BTN)
    public String toUpdate(Model model, Integer id) throws Exception {
        UserDTOResp user = userApi.queryUserById(id);
        model.addAttribute("user",DozerUtil.map(user, UserVOResp.class));
        return "auth/user/user_update";
    }

    /**
     * @Description:修改
     * @Author: Liuwf
     * @Date:
     * @param model:
     * @param id:
     * @return: java.lang.String
     **/
    @RequestMapping("toAuth")
    @RequiresPermissions(value = PermissionsCode.USER_AUTH_BTN)
    public String toAuth(Model model, Integer id) throws Exception {
        UserDTOResp userDTOResp = userApi.queryUserById(id);
        List<RoleDTOResp> roleDTORespList = roleApi.queryRoleList();
        model.addAttribute("user",DozerUtil.map(userDTOResp, UserVOResp.class));
        model.addAttribute("roleList",DozerUtil.mapList(roleDTORespList, UserVOResp.class));
        return "auth/user/user_auth";
    }

    /**
     * @Description: 去忘记密码页面
     * @Author: Liuwf
     * @Date:

     * @return: java.lang.String
     **/
    @RequestMapping("findPassword")
    public String findPassword(){
        return "auth/user/user_find";
    }

    /**
     * @Description:修改密码
     * @Author: Liuwf
     * @Date:
     * @param userName:
     * @param model:
     * @return: java.lang.String
     **/
    @RequestMapping("toUpdatePassword")
    private String toUpdatePassword(String userName, Model model) throws Exception {
        model.addAttribute("salt", PasswordSecurityUtil.generateSalt());
        model.addAttribute("userName", userName);
        return "auth/user/user_update_password";
    }


}
