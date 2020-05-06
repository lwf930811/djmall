package com.dj.mall.admin.vo.auth.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.admin.vo.auth.user
 * @ClassName: UserVOResp
 * @Author: Liuwf
 * @Description: 返回VO
 * @Date: 2020/3/24 19:22
 * @Version: 1.0
 */
@Data
public class UserVOResp implements Serializable {
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
     * @Description:状态 1:未激活 2:已激活
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer status;

    /**
     * @Description:注册时间
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime registerTime;
    /**
     * @Description:用户是否被删除
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer isDel;
    /**
     * @Description:登录时间展示
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
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
     * @Description:手机验证码
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String verify;



}
