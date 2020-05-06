package com.dj.mall.model.dto.dict.attr;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description:属性DTO
 * @Author: Liuwf
 * @Date:
 * @param
 * @return: null
 **/
@Data
@Accessors(chain = true)
public class AttrValueDTOReq implements Serializable {

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
