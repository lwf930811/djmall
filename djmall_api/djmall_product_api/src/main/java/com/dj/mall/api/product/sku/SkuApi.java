package com.dj.mall.api.product.sku;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.dto.product.ProductSkuDTOReq;
import com.dj.mall.model.dto.product.ProductSkuDTOResp;

import java.util.List;

public interface SkuApi  {

    /**
     * @Description:批量新增商品sku
     * @Author: Liuwf
     * @Date:
     * @param list:
     * @return: void
     **/
    void saveBatchProductSku(List<ProductSkuDTOReq> list) throws BusinessException, Exception;
    /**
     * @Description:根据商品ID查找sku属性集合
     * @Author: Liuwf
     * @Date:
     * @param productId:
     * @return: java.util.List<com.dj.mall.model.dto.product.ProductSkuDTOResp>
     **/
    List<ProductSkuDTOResp> querySkuByProductId(Integer productId) throws BusinessException, Exception;

    /**
     * @Description:根据ID查找sku对象
     * @Author: Liuwf
     * @Date:
     * @param id:
     * @return: com.dj.mall.model.dto.product.ProductSkuDTOResp
     **/
    ProductSkuDTOResp querySkuById(Integer id) throws  BusinessException,Exception;

    /**
     * @Description:修改sku库存
     * @Author: Liuwf
     * @Date:
     * @param productSkuDTOReq:
     * @return: void
     **/
    void updateSkuCount (ProductSkuDTOReq productSkuDTOReq) throws BusinessException, Exception;

    /**
     * @Description:修改sku
     * @Author: Liuwf
     * @Date:
     * @param productSkuDTOReq:
     * @return: void
     **/
    void updateSku (ProductSkuDTOReq productSkuDTOReq) throws BusinessException, Exception;

    /**
     * @Description:设置默认值
     * @Author: Liuwf
     * @Date:
     * @param id:
     * @param isDefault:
     * @return: void
     **/
    void updateDefault(Integer id, Integer isDefault) throws BusinessException,Exception;

    /**
     * @Description:sku下架
     * @Author: Liuwf
     * @Date:
     * @param id:
     * @return: void
     **/
    void updateSatus(Integer id) throws BusinessException,Exception;

    /**
     * @Description:商品上下架及sku上下架
     * @Author: Liuwf
     * @Date:
     * @param productId:
     * @param skuStatus:
     * @return: void
     **/
    void updateUpOrDown(Integer productId, Integer skuStatus);
}
