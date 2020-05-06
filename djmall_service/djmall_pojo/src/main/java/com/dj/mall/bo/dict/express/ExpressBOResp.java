package com.dj.mall.bo.dict.express;

import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.bo.dict.express
 * @ClassName: ExpressBOResp
 * @Author: Liuwf
 * @Description: 联表字段
 * @Date: 2020/4/11 13:22
 * @Version: 1.0
 */
@Data
public class ExpressBOResp implements Serializable {

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
    private String logisticsCompanyName;

    /**
     * @Description:快递费
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String expressageName;


}
