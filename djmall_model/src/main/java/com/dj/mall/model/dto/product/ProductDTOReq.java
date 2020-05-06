package com.dj.mall.model.dto.product;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.model.dto.product
 * @ClassName: ProductDTOReq
 * @Author: Liuwf
 * @Description: 商品
 * @Date: 2020/4/26 21:26
 * @Version: 1.0
 */
@Data
public class ProductDTOReq implements Serializable {

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
     * @Description:sku属性及属性值集合
     * @Author: Liuwf
     * @Date:
     * @param null: 
     * @return: null
     **/
    private List<ProductSkuDTOReq> skuList;

    /**
     * @Description:当前页
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer pageNo = 1;

    /**
     * @Description:每页条数
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer pageSize = 10;



}
