package com.dj.mall.admin.vo.dict.express;

import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.admin.vo.dict
 * @ClassName: ExpressVOReq
 * @Author: Liuwf
 * @Description: 快递VO
 * @Date: 2020/4/11 11:17
 * @Version: 1.0
 */
@Data
public class ExpressVOReq implements Serializable {
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
