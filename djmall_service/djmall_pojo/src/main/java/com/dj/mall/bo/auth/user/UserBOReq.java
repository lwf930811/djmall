package com.dj.mall.bo.auth.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.bo.auth.user
 * @ClassName: UserBoReq
 * @Author: Liuwf
 * @Description: UserBO
 * @Date: 2020/4/3 19:45
 * @Version: 1.0
 */
@Data
public class UserBOReq implements Serializable {

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
     * @Description:状态 1:未激活 2:已激活
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer status;

    /**
     * @Description:用户是否被删除
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer isDel;

    /**
     * @Description:当前页
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer pageNo = 1;
    /**
     * @Description:每页条数
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer pageSize = 10;




}
