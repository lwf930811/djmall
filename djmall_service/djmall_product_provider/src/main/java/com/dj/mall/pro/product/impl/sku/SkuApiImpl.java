package com.dj.mall.pro.product.impl.sku;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.api.product.sku.SkuApi;
import com.dj.mall.entity.product.ProductSkuEntity;
import com.dj.mall.mapper.product.sku.SkuMapper;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.constant.SystemConstant;
import com.dj.mall.model.dto.product.ProductSkuDTOReq;
import com.dj.mall.model.dto.product.ProductSkuDTOResp;
import com.dj.mall.model.util.DozerUtil;
import java.util.List;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.pro.product.impl.sku
 * @ClassName: SkuApiImpl
 * @Author: Liuwf
 * @Description: 商品sku实现类
 * @Date: 2020/4/29 12:18
 * @Version: 1.0
 */
@Service
public class SkuApiImpl extends ServiceImpl<SkuMapper, ProductSkuEntity> implements SkuApi {


    /**
     * @param list :
     * @Description:批量新增商品sku
     * @Author: Liuwf
     * @Date:
     * @return: void
     **/
    @Override
    public void saveBatchProductSku(List<ProductSkuDTOReq> list) throws BusinessException, Exception {
        this.saveBatch(DozerUtil.mapList(list,ProductSkuEntity.class));
    }

    /**
     * @param productId :
     * @Description:根据商品ID查找sku属性集合
     * @Author: Liuwf
     * @Date:
     * @return: java.util.List<com.dj.mall.model.dto.product.ProductSkuDTOResp>
     **/
    @Override
    public List<ProductSkuDTOResp> querySkuByProductId(Integer productId) throws BusinessException, Exception {
            QueryWrapper<ProductSkuEntity> productSkuEntityQueryWrapper = new QueryWrapper<>();
            productSkuEntityQueryWrapper.eq("product_id",productId);
            return DozerUtil.mapList(this.list(productSkuEntityQueryWrapper),ProductSkuDTOResp.class);
    }

    /**
     * @param id :
     * @Description:根据ID查找sku对象
     * @Author: Liuwf
     * @Date:
     * @return: com.dj.mall.model.dto.product.ProductSkuDTOResp
     **/
    @Override
    public ProductSkuDTOResp querySkuById(Integer id) throws BusinessException, Exception {
        QueryWrapper<ProductSkuEntity> productSkuEntityQueryWrapper = new QueryWrapper<>();
        productSkuEntityQueryWrapper.eq("id",id);
        return DozerUtil.map(this.getOne(productSkuEntityQueryWrapper),ProductSkuDTOResp.class);
    }

    /**
     * @param productSkuDTOReq :
     * @Description:修改sku库存
     * @Author: Liuwf
     * @Date:
     * @return: void
     **/
    @Override
    public void updateSkuCount(ProductSkuDTOReq productSkuDTOReq) throws BusinessException, Exception {
        UpdateWrapper<ProductSkuEntity> productSkuEntityUpdateWrapper = new UpdateWrapper<>();
        productSkuEntityUpdateWrapper.set("sku_count",productSkuDTOReq.getSkuCount());
        productSkuEntityUpdateWrapper.eq("id",productSkuDTOReq.getId());
        this.update(productSkuEntityUpdateWrapper);
    }

    /**
     * @param productSkuDTOReq :
     * @Description:修改sku
     * @Author: Liuwf
     * @Date:
     * @return: void
     **/
    @Override
    public void updateSku(ProductSkuDTOReq productSkuDTOReq) throws BusinessException, Exception {
        UpdateWrapper<ProductSkuEntity> productSkuEntityUpdateWrapper = new UpdateWrapper<>();
        productSkuEntityUpdateWrapper.set("sku_attr_value_names",productSkuDTOReq.getSkuAttrValueNames());
        productSkuEntityUpdateWrapper.set("sku_count",productSkuDTOReq.getSkuCount());
        productSkuEntityUpdateWrapper.set("sku_price",productSkuDTOReq.getSkuPrice());
        productSkuEntityUpdateWrapper.set("sku_rate",productSkuDTOReq.getSkuRate());
        productSkuEntityUpdateWrapper.eq("id",productSkuDTOReq.getId());
        this.update(productSkuEntityUpdateWrapper);
    }

    /**
     * @param id        :
     * @param isDefault :
     * @Description:设置默认值
     * @Author: Liuwf
     * @Date:
     * @return: void
     **/
    @Override
    public void updateDefault(Integer id, Integer isDefault) throws BusinessException, Exception {
        UpdateWrapper<ProductSkuEntity> productSkuEntityUpdateWrapper = new UpdateWrapper<>();
        productSkuEntityUpdateWrapper.set("is_default",isDefault);
        productSkuEntityUpdateWrapper.eq("id",id);
        this.update(productSkuEntityUpdateWrapper);
    }

    /**
     * @param id :
     * @Description:sku下架
     * @Author: Liuwf
     * @Date:
     * @return: void
     **/
    @Override
    public void updateSatus(Integer id) throws BusinessException, Exception {
        UpdateWrapper<ProductSkuEntity> productSkuEntityUpdateWrapper = new UpdateWrapper<>();
        productSkuEntityUpdateWrapper.set("sku_status", SystemConstant.SKU_STATUS_SOLD_OUT);
        productSkuEntityUpdateWrapper.eq("id",id);
        this.update(productSkuEntityUpdateWrapper);

    }

    /**
     * @param productId :
     * @param skuStatus :
     * @Description:商品上下架及sku上下架
     * @Author: Liuwf
     * @Date:
     * @return: void
     **/
    @Override
    public void updateUpOrDown(Integer productId, Integer skuStatus) {
        QueryWrapper<ProductSkuEntity> productSkuEntityQueryWrapper = new QueryWrapper<>();
        productSkuEntityQueryWrapper.eq("product_id",productId);
        List<ProductSkuEntity> list = this.list(productSkuEntityQueryWrapper);
        list.forEach(productSkuEntity -> {
            productSkuEntity.setSkuStatus(skuStatus);
        });
        this.updateBatchById(list);
    }
}
