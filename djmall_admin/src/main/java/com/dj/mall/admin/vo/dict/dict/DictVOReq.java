package com.dj.mall.admin.vo.dict.dict;

import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.admin.vo.dict
 * @ClassName: DictVOReq
 * @Author: Liuwf
 * @Description: 字典VO
 * @Date: 2020/4/3 15:06
 * @Version: 1.0
 */
@Data
public class DictVOReq implements Serializable {


    /**
     * @Description:字典数据编码
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String code;

    /**
     * @Description:字典数据名
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String name;

    /**
     * @Description:字典数据父级编码
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String pCode;


}
