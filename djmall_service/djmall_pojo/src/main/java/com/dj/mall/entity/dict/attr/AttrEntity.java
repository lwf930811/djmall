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
 * @Description:属性
 * @Author: Liuwf
 * @Date:
 * @param
 * @return: null
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("djmall_dict_product_attr")
public class AttrEntity implements Serializable {

    /**
     * @Description:主键id
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    @TableId(type = IdType.AUTO)
    @Mapping("attrId")
    private Integer id;
    /**
     * @Description:商品属性名称
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String attrName;
}
