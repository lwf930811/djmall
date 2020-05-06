package com.dj.mall.entity.dict.sku;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.dozer.Mapping;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("djmall_dict_product_sku_gm")
public class SkuGmEntity implements Serializable {
   /**
    * @Description:通用SKU关系id
    * @Author: Liuwf
    * @Date:
    * @param null:
    * @return: null
    **/
    @TableId(type = IdType.AUTO)
    @Mapping("id")
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

}
