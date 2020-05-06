package com.dj.mall.entity.auth.role;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.entity.auth.role_resource
 * @ClassName: RoleResourceEntity
 * @Author: Liuwf
 * @Description: 角色资源关联实体
 * @Date: 2020/3/27 20:27
 * @Version: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("djmall_auth_role_resource")
public class RoleResourceEntity {
    /**
     * @Description:角色id
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer roleId;

    /**
     * @Description:资源id
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer resourceId;

    /**
     * @Description:是否删除 1：未删除 2：删除
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer isDel;


}
