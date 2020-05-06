package com.dj.mall.pro.dict.impl.attr;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.api.dict.attr.AttrApi;
import com.dj.mall.bo.dict.attr.AttrBOResp;
import com.dj.mall.entity.dict.attr.AttrEntity;
import com.dj.mall.mapper.dict.attr.AttrMapper;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.dto.dict.attr.AttrDTOReq;
import com.dj.mall.model.dto.dict.attr.AttrDTOResp;
import com.dj.mall.model.dto.dict.attr.AttrValueDTOResp;
import com.dj.mall.model.util.DozerUtil;

import java.util.List;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.pro.dict.impl.attr
 * @ClassName: AttrApiImpl
 * @Author: Liuwf
 * @Description: 属性
 * @Date: 2020/4/11 22:03
 * @Version: 1.0
 */
@Service
public class AttrApiImpl extends ServiceImpl<AttrMapper, AttrEntity> implements AttrApi {


    /**
     * @param attrName :
     * @Description:属性名去重
     * @Author: Liuwf
     * @Date:
     * @return: boolean
     **/
    @Override
    public Boolean findAttrName(String attrName) throws Exception, BusinessException {
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("attr_name",attrName);
        AttrEntity attrEntity = this.getOne(wrapper);
        return null == attrEntity ? true : false;
    }

    /**
     * @Description:查询全部属性
     * @Author: Liuwf
     * @Date:
     * @return: java.util.List<com.dj.mall.model.dto.dict.attr.AttrValueDTOResp>
     **/
    @Override
    public List<AttrDTOResp> queryAttrList() throws Exception, BusinessException {
        List<AttrDTOResp> attrDTOResps = getBaseMapper().queryAttrList();
        return DozerUtil.mapList(attrDTOResps,AttrDTOResp.class);
    }

    /**
     * @param attrDTOReq :
     * @Description:属性新增
     * @Author: Liuwf
     * @Date:
     * @return: void
     **/
    @Override
    public void addAttr(AttrDTOReq attrDTOReq) throws Exception, BusinessException {
        AttrEntity attrEntity = new AttrEntity();
        attrEntity.setAttrName(attrDTOReq.getAttrName());
        this.save(attrEntity);
    }

    /**
     * @param id :
     * @Description:查询对象
     * @Author: Liuwf
     * @Date:
     * @return: com.dj.mall.model.dto.dict.attr.AttrDTOResp
     **/
    @Override
    public AttrDTOResp findAttrById(Integer id) throws Exception, BusinessException {
        return DozerUtil.map(this.getById(id),AttrDTOResp.class);
    }

    /**
     * @param productType :
     * @Description:根据商品类型查商品sku属性及属性值
     * @Author: Liuwf
     * @Date:
     * @return: java.util.List<com.dj.mall.model.dto.dict.attr.AttrDTOResp>
     **/
    @Override
    public List<AttrDTOResp> queryProductSkuAttrByProductType(String productType) throws Exception, BusinessException {
        List<AttrBOResp> attrBORespList = this.getBaseMapper().queryProductSkuAttrByProductType(productType);
        return DozerUtil.mapList(attrBORespList, AttrDTOResp.class);
    }


}
