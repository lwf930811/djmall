package com.dj.mall.model.dto.auth.resource;

import lombok.Data;
import org.dozer.Mapping;

import java.io.Serializable;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.model.dto.auth.resource
 * @ClassName: ResourceDTOReq
 * @Author: Liuwf
 * @Description: 资源DTO接收
 * @Date: 2020/3/28 18:31
 * @Version: 1.0
 */
@Data
public class ResourceDTOReq implements Serializable {
    /**
     * @Description:资源id
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer resourceId;

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
     * @Description:删除状态
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer isDel;



}
