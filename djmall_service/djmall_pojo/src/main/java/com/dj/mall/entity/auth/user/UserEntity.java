package com.dj.mall.entity.auth.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dozer.Mapping;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.entity.auth.user
 * @ClassName: UserEntity
 * @Author: Liuwf
 * @Description: 用户实体类
 * @Date: 2020/3/24 18:19
 * @Version: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("djmall_auth_user")
public class UserEntity implements Serializable {
    /**
     * @Description: 用户id
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    @TableId(type = IdType.AUTO)
    @Mapping("userId")
    private  Integer id;
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
     * @Description:手机验证码
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String verify;

    /**
     * @Description:随机生成密码
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String randomPassword;


}
