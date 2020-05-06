package com.dj.mall.model.dto.auth.resource;

import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.admin.vo.auth.role
 * @ClassName: TreeData
 * @Author: Liuwf
 * @Description: ztree数据实体
 * @Date: 2020/3/31 11:21
 * @Version: 1.0
 */
@Data
public class TreeData implements Serializable {

    /**
     * @Description:资源id
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
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
     * @Description:父级id
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer pId;
    /**
     * @Description:是否是父级
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Boolean parent = false;
    /**
     * @Description:复选框的回显
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Boolean checked = false;



}
