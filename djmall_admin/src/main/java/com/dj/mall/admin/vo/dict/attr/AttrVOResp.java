package com.dj.mall.admin.vo.dict.attr;

import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.admin.vo.dict.attr
 * @ClassName: AttrVOResp
 * @Author: Liuwf
 * @Description: 属性VO
 * @Date: 2020/4/11 23:08
 * @Version: 1.0
 */
@Data
public class AttrVOResp implements Serializable {

    /**
     * @Description:商品属性id
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer attrId;
    /**
     * @Description:商品属性id
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String attrName;

    /**
     * @Description:展示的属性值
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String attrValues;

    /**
     * @Description:属性值ID集合
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String attrValueIds;


}
