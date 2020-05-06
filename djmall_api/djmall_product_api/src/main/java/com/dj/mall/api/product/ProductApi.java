package com.dj.mall.api.product;

import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.base.PageResult;
import com.dj.mall.model.dto.product.ProductDTOReq;
import com.dj.mall.model.dto.product.ProductDTOResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductApi {

    /**
     * @Description:添加商品及商品sku
     * @Author: Liuwf
     * @Date:
     * @param productDTOReq:
     * @param bytes:
     * @return: void
     **/
    void addProduct(ProductDTOReq productDTOReq, byte[] bytes) throws BusinessException, Exception;

    /**
     * @Description:商品集合展示
     * @Author: Liuwf
     * @Date:
     * @param productDTOReq:
     * @return: java.util.List<com.dj.mall.model.dto.product.ProductDTOResp>
     **/
    List<ProductDTOResp> queryProductList(ProductDTOReq productDTOReq,Integer level) throws BusinessException, Exception;

    /**
     * @Description:根据ID查找商品对象
     * @Author: Liuwf
     * @Date:
     * @param id:
     * @return: com.dj.mall.model.dto.product.ProductDTOResp
     **/
    ProductDTOResp queryProductById(Integer id) throws BusinessException,Exception;

    /**
     * @Description:修改商品上下架
     * @Author: Liuwf
     * @Date:
     * @param id:
     * @param status:
     * @return: void
     **/
    void updateProductSatus(Integer id, Integer status) throws BusinessException,Exception;

    /**
     * @Description:修改商品
     * @Author: Liuwf
     * @Date:
     * @param productDTOReq:
     * @param bytes:
     * @return: void
     **/
    void updateProduct(ProductDTOReq productDTOReq, byte[] bytes) throws BusinessException,Exception;


    /**
     * @Description:卖家商品首页展示
     * @Author: Liuwf
     * @Date:
     * @param productDTOReq:
     * @param minPrice:
     * @param maxPrice:
     * @param role:
     * @return: com.dj.mall.model.base.PageResult
     **/
    PageResult indexProductList(ProductDTOReq productDTOReq,Integer minPrice,Integer maxPrice,Integer role) throws BusinessException,Exception;

    /**
     * @Description:商品详情
     * @Author: Liuwf
     * @Date:
     * @param id:
     * @return: com.dj.mall.model.dto.product.ProductDTOResp
     **/
    ProductDTOResp productDetails(Integer id) throws BusinessException,Exception;

}
