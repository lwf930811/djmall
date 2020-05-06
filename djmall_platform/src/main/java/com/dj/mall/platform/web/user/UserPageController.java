package com.dj.mall.platform.web.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.api.cmpt.RedisApi;
import com.dj.mall.model.util.PasswordSecurityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @Description:用户视图
 * @Author: Liuwf
 * @Date:
 * @return: null
 **/
@Controller
@RequestMapping("/user/")
public class UserPageController {
    /**
     * @Description:注入redisApi
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    @Reference
    private RedisApi redisApi;

    /**
     * @Description:用户登录页面
     * @Author: Liuwf
     * @Date:
     * @return: java.lang.String
     **/
    @RequestMapping("toLogin")
    public String toLogin() {
        return "/user/user_login";
    }

    /**
     * @Description:去注册
     * @Author: Liuwf
     * @Date:
     * @return: java.lang.String
     **/
    @RequestMapping("toRegister")
    public String toRegister(Model model) throws Exception {
        String salt = PasswordSecurityUtil.generateSalt();
        model.addAttribute("salt", salt);
        return "/user/user_register";
    }





}
