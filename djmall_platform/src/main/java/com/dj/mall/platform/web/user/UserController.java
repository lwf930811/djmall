package com.dj.mall.platform.web.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.api.auth.user.UserApi;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.constant.SystemConstant;
import com.dj.mall.model.dto.auth.user.UserDTOReq;
import com.dj.mall.model.dto.auth.user.UserDTOResp;
import com.dj.mall.model.dto.auth.user.UserTokenResp;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.platform.vo.UserVOReq;
import com.dj.mall.platform.vo.UserVOResp;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.Subject;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:用户视图
 * @Author: Liuwf
 * @Date:
 * @return: null
 **/
@RestController
@RequestMapping("/user/")
public class UserController {

    @Reference
    private UserApi userApi;
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
    @RequestMapping("register")
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
    @RequestMapping("getSalt")
    public ResultModel<Object> getSalt(String userName) throws Exception{
        Assert.hasText(userName, SystemConstant.NOT_NULL);
        UserVOResp userVOResp = DozerUtil.map(userApi.getSalt(userName), UserVOResp.class);
        ResultModel resultModel = new ResultModel().success(true);
        resultModel.setData(userVOResp.getSalt());
        return resultModel;
    }

    /**
     * @Description:登录
     * @Author: Liuwf
     * @Date:
     * @param userVOReq:
     * @param session:
     * @return: com.dj.mall.model.base.ResultModel
     **/
    @RequestMapping("login")
    public ResultModel login(UserVOReq userVOReq, HttpSession session) throws Exception {
        Assert.hasText(userVOReq.getUserName(),SystemConstant.NOT_NULL);
        Assert.hasText(userVOReq.getPassword(), SystemConstant.NOT_NULL);
        Assert.hasText(userVOReq.getPhone(),SystemConstant.NOT_NULL);
        Assert.hasText(userVOReq.getVerify(),SystemConstant.NOT_NULL);
//        使用用户名密码登录
        if(StringUtils.isEmpty(userVOReq.getUserName()) || StringUtils.isEmpty(userVOReq.getPassword())) {
            UserTokenResp userTokenResp = userApi.tokenLoginByNameAndPwd(userVOReq.getUserName(), userVOReq.getPassword());
            return new ResultModel<>().success(userTokenResp);

        }
        if(StringUtils.isEmpty(userVOReq.getPhone()) || StringUtils.isEmpty(userVOReq.getVerify())) {
            UserTokenResp userTokenResp = userApi.tokenLoginByPhoneAndVerify(userVOReq.getPhone(), userVOReq.getVerify());
            return new ResultModel<>().success(userTokenResp);
        }
        throw new BusinessException(SystemConstant.INPUT_ERROR);
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
        UserDTOResp userDTOResp = userApi.getCode(phone);
        if (null == userDTOResp){
            return new ResultModel<>().error(SystemConstant.PHONE_NOT_REGISTER);
        }
        return new ResultModel<>().success();
    }

    
    




}
