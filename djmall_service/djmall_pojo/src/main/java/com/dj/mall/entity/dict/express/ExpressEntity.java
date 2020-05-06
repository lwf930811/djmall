package com.dj.mall.entity.dict.express;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dozer.Mapping;

import java.io.Serializable;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.entity.dict
 * @ClassName: ExpressEntity
 * @Author: Liuwf
 * @Description: 快递实体类
 * @Date: 2020/4/11 11:02
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("djmall_dict_express")
public class ExpressEntity implements Serializable {
    /**
     * @Description:主键id
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    @TableId(type = IdType.AUTO)
    @Mapping("expressId")
    private Integer id;
    /**
     * @Description:物流公司
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String logisticsCompany;

    /**
     * @Description:快递费
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String expressage;



}
