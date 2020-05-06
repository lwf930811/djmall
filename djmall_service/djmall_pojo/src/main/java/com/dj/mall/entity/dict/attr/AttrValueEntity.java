package com.dj.mall.entity.dict.attr;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.dozer.Mapping;

import java.io.Serializable;
/**
 * @Description:属性值
 * @Author: Liuwf
 * @Date:
 * @param null:
 * @return: null
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("djmall_dict_product_attr_value")
public class AttrValueEntity implements Serializable {

   /**
    * @Description:主键id
    * @Author: Liuwf
    * @Date:
    * @param null:
    * @return: null
    **/
    @TableId(type = IdType.AUTO)
    @Mapping("id")
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
