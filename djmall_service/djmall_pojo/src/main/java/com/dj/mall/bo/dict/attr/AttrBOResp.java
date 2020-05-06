package com.dj.mall.bo.dict.attr;

import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.bo.dict.attr
 * @ClassName: AttrBOResp
 * @Author: Liuwf
 * @Description: 属性BO
 * @Date: 2020/4/11 23:06
 * @Version: 1.0
 */
@Data
public class AttrBOResp implements Serializable {
    /**
     * @Description:展示id
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer attrId;

    /**
     * @Description:商品属性名称
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String attrName;

    /**
     * @Description:展示的属性值集合
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
