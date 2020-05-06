package com.dj.mall.admin.web.dict.sku;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.dict.sku.SkuGmVOResp;
import com.dj.mall.api.dict.sku.SkuGmApi;
import com.dj.mall.bo.dict.sku.SkuGmBOResp;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.dto.dict.sku.SkuGmDTOResp;
import com.dj.mall.model.util.DozerUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.admin.web.dict.sku
 * @ClassName: SkuGmController
 * @Author: Liuwf
 * @Description: sku视图
 * @Date: 2020/4/12 13:33
 * @Version: 1.0
 */
@RestController
@RequestMapping("/dict/sku/")
public class SkuGmController {
    @Reference
    private SkuGmApi skuGmApi;

    /**
     * @Description:skulist
     * @Author: Liuwf
     * @Date:
     * @return: com.dj.mall.model.base.ResultModel
     **/
    @RequestMapping("list")
    public ResultModel skuList( ) throws Exception{
        List<SkuGmDTOResp> skuGmDTOReps = skuGmApi.querySkuList();
        return new ResultModel().success(DozerUtil.mapList(skuGmDTOReps, SkuGmBOResp.class));
    }

    /**
     * @Description:属性保存
     * @Author: Liuwf
     * @Date:
     * @param ids:
     * @param productType:
     * @return: com.dj.mall.model.base.ResultModel
     **/
    @RequestMapping("addAttrValues")
    public ResultModel addAttrValues(Integer[] ids, String productType ) throws Exception{
        skuGmApi.addAttrValues(ids,productType);
        return new ResultModel().success();
    }


}
