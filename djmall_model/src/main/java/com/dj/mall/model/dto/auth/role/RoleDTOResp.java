package com.dj.mall.model.dto.auth.role;

import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.model.dto.auth.role
 * @ClassName: RoleDTOResp
 * @Author: Liuwf
 * @Description: DTO返回实体
 * @Date: 2020/3/26 22:22
 * @Version: 1.0
 */
@Data
public class RoleDTOResp implements Serializable {
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
