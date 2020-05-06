package com.dj.mall.model.dto.auth.user;

import com.dj.mall.model.dto.auth.resource.ResourceDTOResp;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.model.dto.auth.user
 * @ClassName: UserDTOResp
 * @Author: Liuwf
 * @Description: DTO返回
 * @Date: 2020/3/24 18:41
 * @Version: 1.0
 */
@Data
public class UserDTOResp implements Serializable {

    /**
     * @Description: 用户id
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer userId;

    /**
     * @Description:用户名
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String userName;

    /**
     * @Description:用户昵称
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String nickName;

    /**
     * @Description:用户密码
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String password;

    /**
     * @Description:密码加盐
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String salt;

    /**
     * @Description:手机号
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String  phone;

    /**
     * @Description:邮箱
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String email;

    /**
     * @Description:用户性别
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String userSex;

    /**
     * @Description:用户角色
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer userRole;


    /**
     * @Description:注册时间
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private LocalDateTime registerTime;

    /**
     * @Description:用户权限集合
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private List<ResourceDTOResp> permissionList;

    /**
     * @Description:登录时间展示
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private LocalDateTime loginTimeShow;

    /**
     * @Description:角色id
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer roleId;


    /**
     * @Description:角色名
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String roleName;

    /**
     * @Description:状态
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String status;

    /**
     * @Description:手机验证码
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String verify;



}
