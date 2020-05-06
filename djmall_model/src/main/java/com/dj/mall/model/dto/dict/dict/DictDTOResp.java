package com.dj.mall.model.dto.dict.dict;

import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.model.dto.dict
 * @ClassName: DictDTOResp
 * @Author: Liuwf
 * @Description: 字典DTO返回
 * @Date: 2020/4/3 15:04
 * @Version: 1.0
 */
@Data
public class DictDTOResp implements Serializable {


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
