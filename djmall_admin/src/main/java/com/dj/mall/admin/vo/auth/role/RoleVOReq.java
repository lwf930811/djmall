package com.dj.mall.admin.vo.auth.role;

import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.admin.web.auth.role
 * @ClassName: RoleVOReq
 * @Author: Liuwf
 * @Description: VO视图接收
 * @Date: 2020/3/26 22:23
 * @Version: 1.0
 */
@Data
public class RoleVOReq implements Serializable {
    /**
     * @Description:角色id
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer roleId;
    /**
     * @Description:角色名称
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String roleName;


}
