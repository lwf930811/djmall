package com.dj.mall.api.dict.attr;

import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.dto.dict.attr.AttrDTOReq;
import com.dj.mall.model.dto.dict.attr.AttrDTOResp;
import com.dj.mall.model.dto.dict.attr.AttrValueDTOResp;
import javafx.scene.chart.BubbleChart;

import java.util.List;

public interface AttrApi {

    /**
     * @Description:属性名去重
     * @Author: Liuwf
     * @Date:
     * @param attrName:
     * @return: boolean
     **/
    Boolean findAttrName(String attrName) throws Exception, BusinessException;

    /**
     * @Description:查询全部属性
     * @Author: Liuwf
     * @Date:
     * @return: java.util.List<com.dj.mall.model.dto.dict.attr.AttrValueDTOResp>
     **/
    List<AttrDTOResp> queryAttrList() throws Exception, BusinessException;

    /**
     * @Description:属性新增
     * @Author: Liuwf
     * @Date:
     * @param attrDTOReq:
     * @return: void
     **/
    void addAttr(AttrDTOReq attrDTOReq) throws Exception,BusinessException;

    /**
     * @Description:查询对象
     * @Author: Liuwf
     * @Date:
     * @param id:
     * @return: com.dj.mall.model.dto.dict.attr.AttrDTOResp
     **/
    AttrDTOResp findAttrById(Integer id) throws Exception,BusinessException;

    /**
     * @Description:根据商品类型查商品sku属性及属性值
     * @Author: Liuwf
     * @Date:
     * @param productType:
     * @return: java.util.List<com.dj.mall.model.dto.dict.attr.AttrDTOResp>
     **/
    List<AttrDTOResp> queryProductSkuAttrByProductType(String productType) throws Exception, BusinessException;



}
