package com.dj.mall.entity.auth.user;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.entity.auth.user_role
 * @ClassName: UserRoleEntity
 * @Author: Liuwf
 * @Description: 用户角色关联实体类
 * @Date: 2020/3/27 20:25
 * @Version: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("djmall_auth_user_role")
public class UserRoleEntity {

    /**
     * @Description:用户id
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer userId;

    /**
     * @Description:角色id
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer roleId;

    /**
     * @Description:是否删除 1：未删除 2：删除
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer isDel;


}
