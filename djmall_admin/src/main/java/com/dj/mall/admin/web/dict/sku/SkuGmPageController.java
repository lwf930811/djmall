package com.dj.mall.admin.web.dict.sku;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.api.dict.attr.AttrApi;
import com.dj.mall.api.dict.sku.SkuGmApi;
import com.dj.mall.model.dto.dict.attr.AttrDTOResp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.admin.web.dict.sku
 * @ClassName: SkuGmPageController
 * @Author: Liuwf
 * @Description: sku视图
 * @Date: 2020/4/12 13:39
 * @Version: 1.0
 */
@Controller
@RequestMapping("/dict/sku/")
public class SkuGmPageController {
    @Reference
    private SkuGmApi skuGmApi;
    @Reference
    private AttrApi attrApi;


    /**
     * @Description:通用商品类型展示
     * @Author: Liuwf
     * @Date:
     * @return: java.lang.String
     **/
    @RequestMapping("toList")
    public String toList( ) throws Exception {
        return "dict/sku/sku_gm_list";
    }

    /**
     * @Description:属性值展示
     * @Author: Liuwf
     * @Date:
     * @return: java.lang.String
     **/
    @RequestMapping("toValueList")
    public String toValueList(String productType, Model model) throws Exception {
        List<Integer> attrIds = skuGmApi.findAttrIdByProductType(productType);
        List<AttrDTOResp> attrList = attrApi.queryAttrList();
        model.addAttribute("productType",productType);
        model.addAttribute("attrList",attrList);
        model.addAttribute("attrIds",attrIds);
        return "dict/sku/attr_value_list";
    }


}
