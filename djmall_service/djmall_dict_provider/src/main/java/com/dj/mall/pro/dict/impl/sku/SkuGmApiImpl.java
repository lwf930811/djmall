package com.dj.mall.pro.dict.impl.sku;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.api.dict.sku.SkuGmApi;
import com.dj.mall.bo.dict.sku.SkuGmBOResp;
import com.dj.mall.entity.dict.sku.SkuGmEntity;
import com.dj.mall.mapper.dict.sku.SkuGmMapper;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.dto.dict.sku.SkuGmDTOResp;
import com.dj.mall.model.util.DozerUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.pro.dict.impl.sku
 * @ClassName: SkuGmApiImpl
 * @Author: Liuwf
 * @Description: sku实现
 * @Date: 2020/4/12 13:32
 * @Version: 1.0
 */
@Service
public class SkuGmApiImpl extends ServiceImpl<SkuGmMapper, SkuGmEntity> implements SkuGmApi {


    /**
     * @Description:查询sku集合
     * @Author: Liuwf
     * @Date:
     * @return: java.util.List<com.dj.mall.model.dto.dict.sku.SkuGmDTOReps>
     **/
    @Override
    public List<SkuGmDTOResp> querySkuList() throws Exception, BusinessException {
        List<SkuGmDTOResp> skuGmDTOResps = getBaseMapper().querySkuList();
        return DozerUtil.mapList(skuGmDTOResps,SkuGmDTOResp.class);
    }

    /**
     * @param productType :
     * @Description:根据商品类型查找属性id
     * @Author: Liuwf
     * @Date:
     * @return: java.util.List<java.lang.Integer>
     **/
    @Override
    public List<Integer> findAttrIdByProductType(String productType) throws Exception, BusinessException {
        QueryWrapper<SkuGmEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_type",productType);
        List<SkuGmEntity> skugm = this.list(queryWrapper);
        ArrayList<Integer> attrIds = new ArrayList<>();
        skugm.forEach(skugms ->  attrIds.add(skugms.getAttrId()));
        return attrIds;
    }

    /**
     * @param ids         :
     * @param productType :
     * @Description:增加商品类型的属性
     * @Author: Liuwf
     * @Date:
     * @return: void
     **/
    @Override
    public void addAttrValues(Integer[] ids, String productType) throws Exception, BusinessException {
        QueryWrapper<SkuGmEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("product_type",productType);
        this.remove(wrapper);
        ArrayList<SkuGmEntity> skuGmEntities = new ArrayList<>();
        for (Integer id : ids) {
            SkuGmEntity skuGmEntity = new SkuGmEntity();
            skuGmEntity.setAttrId(id);
            skuGmEntity.setProductType(productType);
            skuGmEntities.add(skuGmEntity);
        }
        this.saveBatch(skuGmEntities);

    }
}
