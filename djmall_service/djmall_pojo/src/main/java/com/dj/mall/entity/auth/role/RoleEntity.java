package com.dj.mall.entity.auth.role;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dozer.Mapping;

import java.io.Serializable;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.entity.auth.role
 * @ClassName: RoleEntity
 * @Author: Liuwf
 * @Description: 角色实体类
 * @Date: 2020/3/26 22:14
 * @Version: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("djmall_auth_role")
public class RoleEntity implements Serializable {
    /**
     * @Description:角色id
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    @TableId(type = IdType.AUTO)
    @Mapping("roleId")
    private Integer id;
    /**
     * @Description:角色名称
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String roleName;

    /**
     * @Description:是否删除
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer isDel;


}
