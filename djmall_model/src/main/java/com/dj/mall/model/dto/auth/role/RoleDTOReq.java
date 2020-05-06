package com.dj.mall.model.dto.auth.role;

import lombok.Data;

import java.awt.*;
import java.io.Serializable;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.model.dto.auth.role
 * @ClassName: RoleDTOReq
 * @Author: Liuwf
 * @Description: 角色DTO接收
 * @Date: 2020/3/26 22:19
 * @Version: 1.0
 */
@Data
public class RoleDTOReq implements Serializable {
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

    /**
     * @Description:用户是否被删除
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer isDel;

}
