package com.dj.mall.api.dict.sku;

import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.dto.dict.sku.SkuGmDTOResp;

import java.util.List;

public interface SkuGmApi {

    /**
     * @Description:查询sku集合
     * @Author: Liuwf
     * @Date:
     * @return: java.util.List<com.dj.mall.model.dto.dict.sku.SkuGmDTOReps>
     **/
    List<SkuGmDTOResp> querySkuList() throws Exception, BusinessException;

    /**
     * @Description:根据商品类型查找属性id
     * @Author: Liuwf
     * @Date:
     * @param productType:
     * @return: java.util.List<java.lang.Integer>
     **/
    List<Integer> findAttrIdByProductType(String productType) throws Exception, BusinessException;

    /**
     * @Description:增加商品类型的属性
     * @Author: Liuwf
     * @Date:  
     * @param ids: 
     * @param productType: 
     * @return: void
     **/
    void addAttrValues(Integer[] ids,String productType) throws Exception,BusinessException;

}
