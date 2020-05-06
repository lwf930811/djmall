package com.dj.mall.model.dto.product;

import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.model.dto.product
 * @ClassName: ProductDTOResp
 * @Author: Liuwf
 * @Description: 商品
 * @Date: 2020/4/26 21:28
 * @Version: 1.0
 */
@Data
public class ProductDTOResp implements Serializable {

    /**
     * @Description:商品id
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer id;

    /**
     * @Description:用户id
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer userId;

    /**
     * @Description:商品名称
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String productName;

    /**
     * @Description:商品类型(code)
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String productType;
    /**
     * @Description:商品类型名
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String productTypeName;

    /**
     * @Description:上下架状态 1上架 -1 下架
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer status;

    /**
     * @Description:商品运费id
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer productExpressage;

    /**
     * @Description:商品运费展示
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String productExpressageShow;

    /**
     * @Description:商品图片路径
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String picture;

    /**
     * @Description:商品描述
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String productDescribe;

    /**
     * @Description:点赞量
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer likeNumber;

    /**
     * @Description:订单量
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer orderNumber;

    /**
     * @Description:商品价格
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer skuPrice;
    /**
     * @Description:库存
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer skuCount;

    /**
     * @Description:商品折扣
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer skuRate;



}
