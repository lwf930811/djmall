package com.dj.mall.admin.vo.auth.role;

import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.admin.web.auth.role
 * @ClassName: RoleVOResp
 * @Author: Liuwf
 * @Description: 视图VO返回
 * @Date: 2020/3/26 22:25
 * @Version: 1.0
 */
@Data
public class RoleVOResp implements Serializable {
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
