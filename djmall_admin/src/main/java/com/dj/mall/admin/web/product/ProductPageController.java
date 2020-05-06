package com.dj.mall.admin.web.product;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.dict.dict.DictVOResp;
import com.dj.mall.admin.vo.dict.express.ExpressVOResp;
import com.dj.mall.admin.vo.product.ProductSkuVOResp;
import com.dj.mall.admin.vo.product.ProductVOResp;
import com.dj.mall.api.cmpt.RedisApi;
import com.dj.mall.api.dict.dict.DictApi;
import com.dj.mall.api.dict.express.ExpressApi;
import com.dj.mall.api.product.ProductApi;
import com.dj.mall.api.product.sku.SkuApi;
import com.dj.mall.model.constant.SystemConstant;
import com.dj.mall.model.dto.dict.dict.DictDTOResp;
import com.dj.mall.model.dto.dict.express.ExpressDTOResp;
import com.dj.mall.model.dto.product.ProductDTOResp;
import com.dj.mall.model.dto.product.ProductSkuDTOResp;
import com.dj.mall.model.util.DozerUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.admin.web.product
 * @ClassName: ProductPageController
 * @Author: Liuwf
 * @Description: 商品管理页面控制层
 * @Date: 2020/4/24 21:10
 * @Version: 1.0
 */
@Controller
@RequestMapping("/product/")
public class ProductPageController {
    @Reference
    private DictApi dictApi;
    @Reference
    private RedisApi redisApi;
    @Reference
    private ExpressApi expressApi;
    @Reference
    private ProductApi productApi;
    @Reference
    private SkuApi skuApi;



    /**
     * @Description:商品list页面
     * @Author: Liuwf
     * @Date:
     * @param model:
     * @return: java.lang.String
     **/
    @RequestMapping("toList")
    public String toList(Model model) throws Exception {
        List<DictDTOResp> redisProductList = redisApi.getHashValues(SystemConstant.DICT_P_CODE_PRODUCT_TYPE);
        if (null == redisProductList || redisProductList.size() == 0){
            List<DictDTOResp> dtoRespList = dictApi.queryDictNameByPcode(SystemConstant.DICT_P_CODE_PRODUCT_TYPE);
            model.addAttribute("productTypeList", DozerUtil.mapList(dtoRespList, DictVOResp.class));
        } else {
            model.addAttribute("productTypeList",DozerUtil.mapList(redisProductList,DictVOResp.class));
        }
        return "product/product_list";
    }

    /**
     * @Description:商品增加页面
     * @Author: Liuwf
     * @Date:
     * @param model:
     * @return: java.lang.String
     **/
    @RequestMapping("toAddProduct")
    public String toAddProduct(Model model) throws Exception {
//        获取所有商品类型
        List<DictDTOResp> redisProductList = redisApi.getHashValues(SystemConstant.DICT_P_CODE_PRODUCT_TYPE);
//        获取所有的邮费
        List<ExpressDTOResp> expressDTOResps = expressApi.queryExpress();
        model.addAttribute("productTypeList", DozerUtil.mapList(redisProductList, DictVOResp.class));
        model.addAttribute("expressageList", DozerUtil.mapList(expressDTOResps, ExpressVOResp.class));
        return "product/product_add";
    }

    /**
     * @Description:商品修改页面
     * @Author: Liuwf
     * @Date:
     * @param id:
     * @param model:
     * @return: java.lang.String
     **/
    @RequestMapping("toUpdate")
    public String toUpdate(Integer id,Model model) throws Exception {
//        获取所有的邮费
        List<ExpressDTOResp> expressDTOResps = expressApi.queryExpress();
        model.addAttribute("expressageList", DozerUtil.mapList(expressDTOResps, ExpressVOResp.class));
//        根据ID查找商品集合
        ProductDTOResp productDTOResp = productApi.queryProductById(id);
        model.addAttribute("product", DozerUtil.map(productDTOResp, ProductVOResp.class));
        DictDTOResp dictDTOResp = dictApi.queryDictByCode(productDTOResp.getProductType());
        model.addAttribute("dict", DozerUtil.map(dictDTOResp, DictVOResp.class));
//        根据商品ID查找skulist
        List<ProductSkuDTOResp> productSkuDTOResps = skuApi.querySkuByProductId(id);
        model.addAttribute("skuList", DozerUtil.mapList(productSkuDTOResps, ProductSkuVOResp.class));
        return "product/product_update";
    }

    /**
     * @Description:sku库存修改
     * @Author: Liuwf
     * @Date:
     * @param id:
     * @param model:
     * @return: java.lang.String
     **/
    @RequestMapping("count/toUpdate")
    public String toUpdateCont(Integer id,Model model) throws Exception {
        ProductSkuDTOResp productSkuDTOResp = skuApi.querySkuById(id);
        model.addAttribute("sku", DozerUtil.map(productSkuDTOResp, ProductSkuVOResp.class));
        return "product/sku/count_update";
    }
    @RequestMapping("sku/toUpdate")
    public String toUpdateSku(Integer id,Model model) throws Exception {
        ProductSkuDTOResp productSkuDTOResp = skuApi.querySkuById(id);
        model.addAttribute("sku", DozerUtil.map(productSkuDTOResp, ProductSkuVOResp.class));
        return "product/sku/sku_update";
    }




}
