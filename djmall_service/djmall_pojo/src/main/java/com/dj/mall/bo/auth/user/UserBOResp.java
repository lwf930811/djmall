package com.dj.mall.bo.auth.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.bo.auth.user
 * @ClassName: UserBOResp
 * @Author: Liuwf
 * @Description: UserBO
 * @Date: 2020/4/3 19:46
 * @Version: 1.0
 */
@Data
public class UserBOResp implements Serializable {

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
    private String userRole;

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


}
