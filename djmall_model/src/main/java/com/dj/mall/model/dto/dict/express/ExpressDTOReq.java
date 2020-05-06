package com.dj.mall.model.dto.dict.express;

import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.model.dto.dict
 * @ClassName: ExpressDTOReq
 * @Author: Liuwf
 * @Description: 快递DTO
 * @Date: 2020/4/11 11:13
 * @Version: 1.0
 */
@Data
public class ExpressDTOReq implements Serializable {
    /**
     * @Description:主键id
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer expressId;
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
