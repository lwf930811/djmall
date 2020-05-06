package com.dj.mall.admin.vo.dict.attr;

import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.admin.vo.dict.attr
 * @ClassName: AttrValueVOResp
 * @Author: Liuwf
 * @Description: 属性
 * @Date: 2020/4/12 9:00
 * @Version: 1.0
 */
@Data
public class AttrValueVOResp implements Serializable {

    /**
     * @Description:商品属性值id
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer id;

    /**
     * @Description:商品属性id
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer attrId;

    /**
     * @Description:属性值
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String attrValue;


}
