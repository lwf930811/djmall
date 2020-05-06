package com.dj.mall.admin.vo.dict.sku;

import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.admin.vo.dict.sku
 * @ClassName: SkuGmVOResp
 * @Author: Liuwf
 * @Description: skuVO
 * @Date: 2020/4/12 13:29
 * @Version: 1.0
 */
@Data
public class SkuGmVOResp implements Serializable {
    /**
     * @Description:通用SKU关系id
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer id;
    /**
     * @Description:商品类型
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String productType;
    /**
     * @Description:属性ID
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer attrId;

    /**
     * @Description:展示类型名字
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String typeName;

    /**
     * @Description:展示属性名字
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String attrNames;



}
