package com.dj.mall.admin.vo.product;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.admin.vo.product
 * @ClassName: ProductSkuVOReq
 * @Author: Liuwf
 * @Description: 商品sku
 * @Date: 2020/4/24 22:00
 * @Version: 1.0
 */
@Data
public class ProductSkuVOReq implements Serializable {

    /**
     * @Description:主键ID
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer id;

    /**
     * @Description:商品ID
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer productId;

    /**
     * @Description:SKU价格
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private BigDecimal skuPrice;

    /**
     * @Description:sku库存
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer skuCount;

    /**
     * @Description:SKU折扣,0表示无折扣
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer skuRate;

    /**
     * @Description:SKU状态[0下架,1上架]
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer skuStatus;

    /**
     * @Description:SKU属性ID集合[id1:id2],-1代表自定
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String skuAttrIds;

    /**
     * @Description:SKU属性名集合[name1:name2]
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String skuAttrNames;

    /**
     * @Description:SKU属性值ID集合[id1:id2]-1代表自定
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String skuAttrValueIds;

    /**
     * @Description:SKU属性值ID集合[id1:id2]-1代表自定
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String skuAttrValueNames;

    /**
     * @Description:是否默认
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer isDefault;



}
