package com.dj.mall.model.dto.auth.resource;

import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.model.dto.auth.resource
 * @ClassName: ResourceDTOresp
 * @Author: Liuwf
 * @Description: 资源DTO返回
 * @Date: 2020/3/28 18:33
 * @Version: 1.0
 */
@Data
public class ResourceDTOResp implements Serializable {

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
     * @Description:资源编码
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String resourceCode;

    /**
     * @Description:资源类型  1菜单 2按钮
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer resourceType;


}
