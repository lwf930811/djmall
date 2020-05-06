package com.dj.mall.api.dict.attr;

import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.dto.dict.attr.AttrDTOReq;
import com.dj.mall.model.dto.dict.attr.AttrValueDTOReq;
import com.dj.mall.model.dto.dict.attr.AttrValueDTOResp;

import java.util.List;

public interface AttrValueApi {

    /**
     * @Description:属性值新增
     * @Author: Liuwf
     * @Date:
     * @param attrValueDTOReq:
     * @return: void
     **/
    void addAttrValue(AttrValueDTOReq attrValueDTOReq) throws Exception, BusinessException;

    /**
     * @Description:根据属性查找属性集合
     * @Author: Liuwf
     * @Date:
     * @param attrId:
     * @return: java.util.List<com.dj.mall.model.dto.dict.attr.AttrValueDTOResp>
     **/
    List<AttrValueDTOResp> queryValueByAttrId(Integer attrId) throws Exception,BusinessException;

    /**
     * @Description:删除属性值
     * @Author: Liuwf
     * @Date:
     * @param id:
     * @return: void
     **/
    void removeValue(Integer id) throws Exception, BusinessException;
}
