package com.dj.mall.platform.web.index;
import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.api.cmpt.RedisApi;
import com.dj.mall.api.product.ProductApi;
import com.dj.mall.api.product.sku.SkuApi;
import com.dj.mall.model.constant.SystemConstant;
import com.dj.mall.model.dto.dict.dict.DictDTOResp;
import com.dj.mall.model.dto.product.ProductDTOResp;
import com.dj.mall.model.dto.product.ProductSkuDTOResp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/index/")
public class IndexPageController {

    @Reference
    private RedisApi redisApi;
    @Reference
    private SkuApi skuApi;
    @Reference
    private ProductApi productApi;


    /**
     * 去主页
     * @param model
     * @return
     */
    @RequestMapping("toIndex")
    public String toTop(Model model) {
        List<DictDTOResp> dictDTOResp = redisApi.getHashValues(SystemConstant.DICT_P_CODE_PRODUCT_TYPE);
        model.addAttribute("prouctType", dictDTOResp);
        return "platform_index";
    }

    /**
     * @Description:商品详情
     * @Author: Liuwf
     * @Date:
     * @param id:
     * @param model:
     * @return: java.lang.String
     **/
    @RequestMapping("productDetails")
    public String productDetails(Integer id,Model model) throws Exception {
        ProductDTOResp productDTOResp = productApi.productDetails(id);
        model.addAttribute("product",productDTOResp);
        List<ProductSkuDTOResp> productSkuDTOResps = skuApi.querySkuByProductId(id);
        model.addAttribute("sku",productSkuDTOResps);
        return "index/product_details";
    }

}
