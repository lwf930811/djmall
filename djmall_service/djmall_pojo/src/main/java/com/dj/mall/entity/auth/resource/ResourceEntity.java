package com.dj.mall.entity.auth.resource;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dozer.Mapping;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.entity.auth.resource
 * @ClassName: ResourceEntity
 * @Author: Liuwf
 * @Description: 资源实体
 * @Date: 2020/3/27 20:05
 * @Version: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("djmall_auth_resource")
public class ResourceEntity {
    /**
     * @Description:资源id
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    @TableId(type = IdType.AUTO)
    @Mapping("resourceId")
    private Integer id;

    /**
     * @Description:资源名称
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String resourceName;

   /**
    * @Description:资源PATH
    * @Author: Liuwf
    * @Date:
    * @param null:
    * @return: null
    **/
    private String url;

   /**
    * @Description:父级id
    * @Author: Liuwf
    * @Date:
    * @param null:
    * @return: null
    **/
    private Integer pId;

    /**
     * @Description:资源类型  1菜单 2按钮
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer resourceType;

    /**
     * @Description:资源编码
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String resourceCode;

    /**
     * @Description:是否删除 1正常 0删除
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer isDel;


}
