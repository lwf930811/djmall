package com.dj.mall.pro.product.impl;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.api.cmpt.QiNiuYunApi;
import com.dj.mall.api.product.ProductApi;
import com.dj.mall.api.product.sku.SkuApi;
import com.dj.mall.bo.product.ProductBO;
import com.dj.mall.entity.product.ProductEntity;
import com.dj.mall.mapper.product.ProductMapper;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.base.PageResult;
import com.dj.mall.model.constant.SystemConstant;
import com.dj.mall.model.dto.product.ProductDTOReq;
import com.dj.mall.model.dto.product.ProductDTOResp;
import com.dj.mall.model.dto.product.ProductSkuDTOReq;
import com.dj.mall.model.util.DozerUtil;
import org.jboss.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.pro.product.impl
 * @ClassName: ProductApiImpl
 * @Author: Liuwf
 * @Description: 商品实现类
 * @Date: 2020/4/27 17:13
 * @Version: 1.0
 */
@Service
public class ProductApiImpl extends ServiceImpl<ProductMapper, ProductEntity> implements ProductApi {
    @Reference
    private QiNiuYunApi qiNiuYunApi;
    @Autowired
    private SkuApi skuApi;


    /**
     * @param productDTOReq :
     * @param bytes         :
     * @Description:添加商品及商品sku
     * @Author: Liuwf
     * @Date:
     * @return: void
     **/
    @Override
    public void addProduct(ProductDTOReq productDTOReq, byte[] bytes) throws BusinessException, Exception {
//       保存图片名称
        String upload = qiNiuYunApi.upload(bytes);
//       保存图片名称
        productDTOReq.setPicture(upload);
        productDTOReq.setLikeNumber(SystemConstant.START_LIKE_NUMBER);
        productDTOReq.setOrderNumber(SystemConstant.START_ORDER_NUMBER);
        ProductEntity productEntity = DozerUtil.map(productDTOReq, ProductEntity.class);
        this.save(productEntity);
        List<ProductSkuDTOReq> skuList = productDTOReq.getSkuList();
//      保存商品sku
        skuList.forEach(productSkuDTOReq ->{
            productSkuDTOReq.setSkuStatus(SystemConstant.SKU_STATUS_PUTAWAY);
            productSkuDTOReq.setProductId(productEntity.getId());
        });
        productDTOReq.getSkuList().get(SystemConstant.SKU_INDEX_IS_DEFAULT).setIsDefault(SystemConstant.SKU_IS_DEFAULT);
        skuApi.saveBatchProductSku(DozerUtil.mapList(skuList, ProductSkuDTOReq.class));
    }

    /**
     * @param productDTOReq :
     * @Description:商品集合展示
     * @Author: Liuwf
     * @Date:
     * @return: java.util.List<com.dj.mall.model.dto.product.ProductDTOResp>
     **/
    @Override
    public List<ProductDTOResp> queryProductList(ProductDTOReq productDTOReq,Integer level) throws BusinessException, Exception {
        return DozerUtil.mapList(getBaseMapper().queryProductList(productDTOReq,level),ProductDTOResp.class);

    }

    /**
     * @param id :
     * @Description:根据ID查找商品对象
     * @Author: Liuwf
     * @Date:
     * @return: com.dj.mall.model.dto.product.ProductDTOResp
     **/
    @Override
    public ProductDTOResp queryProductById(Integer id) throws BusinessException, Exception {
        QueryWrapper<ProductEntity> productEntityQueryWrapper = new QueryWrapper<>();
        productEntityQueryWrapper.eq("id",id);
        this.getOne(productEntityQueryWrapper);
        return DozerUtil.map(this.getOne(productEntityQueryWrapper),ProductDTOResp.class);
    }

    /**
     * @param id     :
     * @param status :
     * @Description:修改商品上下架
     * @Author: Liuwf
     * @Date:
     * @return: void
     **/
    @Override
    public void updateProductSatus(Integer id, Integer status) throws BusinessException, Exception {
        UpdateWrapper<ProductEntity> productEntityUpdateWrapper = new UpdateWrapper<>();
        productEntityUpdateWrapper.set("status",status);
        productEntityUpdateWrapper.eq("id",id);
        this.update(productEntityUpdateWrapper);
        skuApi.updateUpOrDown(id,status);


    }

    /**
     * @param productDTOReq :
     * @param bytes         :
     * @Description:修改商品
     * @Author: Liuwf
     * @Date:
     * @return: void
     **/
    @Override
    public void updateProduct(ProductDTOReq productDTOReq, byte[] bytes) throws BusinessException, Exception {
//       保存图片名称
        String upload = qiNiuYunApi.upload(bytes);
//       保存图片名称
        if (!StringUtils.isEmpty(upload)){
            productDTOReq.setPicture(upload);
        }
        this.updateById(DozerUtil.map(productDTOReq,ProductEntity.class));

        
    }

    /**
     * @param productDTOReq :
     * @param minPrice      :
     * @param maxPrice      :
     * @param role         :
     * @Description:卖家商品首页展示
     * @Author: Liuwf
     * @Date:
     * @return: com.dj.mall.model.base.PageResult
     **/
    @Override
    public PageResult indexProductList(ProductDTOReq productDTOReq, Integer minPrice, Integer maxPrice, Integer role) throws BusinessException, Exception {
        IPage<ProductBO> iPage = new Page()
                .setCurrent(productDTOReq.getPageNo())
                .setSize(productDTOReq.getPageSize());
        IPage<ProductBO> pageInfo = getBaseMapper().indexProductList(iPage, productDTOReq, minPrice, maxPrice, role);
        return PageResult.builder().pages(pageInfo.getPages()).list(DozerUtil.mapList(pageInfo.getRecords(),ProductDTOResp.class)).build();
    }

    /**
     * @param id :
     * @Description:商品详情
     * @Author: Liuwf
     * @Date:
     * @return: com.dj.mall.model.dto.product.ProductDTOResp
     **/
    @Override
    public ProductDTOResp productDetails(Integer id) throws BusinessException, Exception {
        ProductBO productBO = getBaseMapper().productDetails(id);
        return DozerUtil.map(productBO,ProductDTOResp.class);
    }
}
