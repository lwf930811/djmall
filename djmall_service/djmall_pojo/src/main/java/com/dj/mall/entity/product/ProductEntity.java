package com.dj.mall.entity.product;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.entity.product
 * @ClassName: ProductEntity
 * @Author: Liuwf
 * @Description: 商品实体类
 * @Date: 2020/4/26 21:20
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("djmall_product")
public class ProductEntity implements Serializable {

    /**
     * @Description:商品id
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    @TableId(type = IdType.AUTO)
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


}
