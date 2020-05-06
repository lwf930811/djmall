package com.dj.mall.admin.web.product;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.product.ProductSkuVOReq;
import com.dj.mall.admin.vo.product.ProductVOReq;
import com.dj.mall.admin.vo.product.ProductVOResp;
import com.dj.mall.api.dict.attr.AttrApi;
import com.dj.mall.api.product.ProductApi;
import com.dj.mall.api.product.sku.SkuApi;
import com.dj.mall.bo.dict.attr.AttrBOResp;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.constant.SystemConstant;
import com.dj.mall.model.dto.auth.user.UserDTOReq;
import com.dj.mall.model.dto.auth.user.UserDTOResp;
import com.dj.mall.model.dto.dict.attr.AttrDTOResp;
import com.dj.mall.model.dto.product.ProductDTOReq;
import com.dj.mall.model.dto.product.ProductDTOResp;
import com.dj.mall.model.dto.product.ProductSkuDTOReq;
import com.dj.mall.model.util.DozerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Session;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.admin.web.product
 * @ClassName: ProductController
 * @Author: Liuwf
 * @Description: 商品管理控制层
 * @Date: 2020/4/24 21:14
 * @Version: 1.0
 */
@RestController
@RequestMapping("/product/")
public class ProductController {

    @Reference
    private AttrApi attrApi;
    @Reference
    private ProductApi productApi;
    @Reference
    private SkuApi skuApi;

    /**
     * @Description:根据商品类型查商品sku属性及属性值
     * @Author: Liuwf
     * @Date:
     * @param productType:
     * @return: com.dj.mall.model.base.ResultModel
     **/
    @RequestMapping("findAttrAndValues")
    public ResultModel findAttrAndValues(String productType) throws Exception{
        List<AttrDTOResp> attrDTORespList = attrApi.queryProductSkuAttrByProductType(productType);
        return new ResultModel().success(DozerUtil.mapList(attrDTORespList, AttrBOResp.class));
    }

    /**
     * @Description:商品新增以及商品sku新增
     * @Author: Liuwf
     * @Date:
     * @param productVOReq:
     * @param session:
     * @return: com.dj.mall.model.base.ResultModel
     **/
    @RequestMapping("addProduct")
    public ResultModel addProduct(ProductVOReq productVOReq, MultipartFile file, HttpSession session) throws Exception{
//        非空判断
        productVOReq.setSkuList(productVOReq.getSkuList().stream().filter(ProductSkuVOReq -> ProductSkuVOReq.getSkuAttrIds() != null).collect(Collectors.toList()));
        UserDTOResp userDTOResp = (UserDTOResp) session.getAttribute(SystemConstant.USER_SESSION);
        productVOReq.setUserId(userDTOResp.getUserId());
        productApi.addProduct(DozerUtil.map(productVOReq,ProductDTOReq.class),file.getBytes());
        return new ResultModel().success();
    }

    /**
     * @Description:商品展示
     * @Author: Liuwf
     * @Date:
     * @param productVOReq:
     * @param level:
     * @param session:
     * @return: com.dj.mall.model.base.ResultModel
     **/
    @RequestMapping("productList")
    public ResultModel productList(ProductVOReq productVOReq, Integer level, HttpSession session) throws Exception{
        UserDTOResp userDTOResp = (UserDTOResp) session.getAttribute(SystemConstant.USER_SESSION);
        List<ProductDTOResp> productDTOResps = productApi.queryProductList(DozerUtil.map(productVOReq, ProductDTOReq.class), userDTOResp.getUserRole());
        return new ResultModel().success(DozerUtil.mapList(productDTOResps, ProductVOResp.class));
    }

    /**
     * @Description:修改库存
     * @Author: Liuwf
     * @Date:
     * @param productSkuVOReq:
     * @return: com.dj.mall.model.base.ResultModel
     **/
    @RequestMapping("sku/updateCount")
    public ResultModel updateCount(ProductSkuVOReq productSkuVOReq) throws Exception{
        skuApi.updateSkuCount(DozerUtil.map(productSkuVOReq, ProductSkuDTOReq.class));
        return new ResultModel().success();
    }

    /**
     * @Description:修改sku
     * @Author: Liuwf
     * @Date:
     * @param productSkuVOReq:
     * @return: com.dj.mall.model.base.ResultModel
     **/
    @RequestMapping("/sku/updateSku")
    public ResultModel updateSku(ProductSkuVOReq productSkuVOReq) throws Exception{
        skuApi.updateSku(DozerUtil.map(productSkuVOReq, ProductSkuDTOReq.class));
        return new ResultModel().success();
    }

    /**
     * @Description:修改
     * @Author: Liuwf
     * @Date:
     * @param id:
     * @param isDefault:
     * @return: com.dj.mall.model.base.ResultModel
     **/
    @RequestMapping("sku/updateDefault")
    public ResultModel updateSku(Integer id, Integer isDefault) throws Exception{
        skuApi.updateDefault(id, isDefault);
        return new ResultModel().success();
    }

    /**
     * @Description:商品上下架
     * @Author: Liuwf
     * @Date:
     * @param id:
     * @param status:
     * @return: com.dj.mall.model.base.ResultModel
     **/
    @RequestMapping("updateStatus")
    public ResultModel updateProductSatus(Integer id, Integer status) throws Exception{
        productApi.updateProductSatus(id,status);
        return new ResultModel().success();
    }

    /**
     * @Description:商品修改
     * @Author: Liuwf
     * @Date:
     * @param productVOReq:
     * @param file:
     * @return: com.dj.mall.model.base.ResultModel
     **/
    @RequestMapping("updateProduct")
    public ResultModel updateProduct(ProductVOReq productVOReq, MultipartFile file) throws Exception{
        productApi.updateProduct(DozerUtil.map(productVOReq,ProductDTOReq.class),file.getBytes());
        return new ResultModel().success();
    }





}
