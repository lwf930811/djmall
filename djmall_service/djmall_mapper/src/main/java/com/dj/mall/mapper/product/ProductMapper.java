package com.dj.mall.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dj.mall.bo.product.ProductBO;
import com.dj.mall.entity.product.ProductEntity;
import com.dj.mall.entity.product.ProductSkuEntity;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.base.PageResult;
import com.dj.mall.model.dto.product.ProductDTOReq;
import com.dj.mall.model.dto.product.ProductDTOResp;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ProductMapper extends BaseMapper<ProductEntity> {

    /**
     * @param productDTOReq:
     * @Description:商品集合展示
     * @Author: Liuwf
     * @Date:
     * @return: java.util.List<com.dj.mall.bo.product.ProductBO>
     **/
    List<ProductBO> queryProductList(@Param("product") ProductDTOReq productDTOReq, @Param("level") Integer level) throws DataAccessException;

    /**
     * @param productDTOReq:
     * @param minPrice:
     * @param maxPrice:
     * @param role:
     * @Description:
     * @Author: Liuwf
     * @Date:
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.dj.mall.bo.product.ProductBO>
     **/
    IPage<ProductBO> indexProductList(IPage page, @Param("product") ProductDTOReq productDTOReq, @Param("minPrice") Integer minPrice, @Param("maxPrice") Integer maxPrice, @Param("role") Integer role) throws DataAccessException;

    /**
     * @Description:商品详情
     * @Author: Liuwf
     * @Date:
     * @param id:
     * @return: com.dj.mall.model.dto.product.ProductDTOResp
     **/
    ProductBO productDetails(Integer id) throws DataAccessException;



}
