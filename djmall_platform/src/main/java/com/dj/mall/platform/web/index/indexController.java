package com.dj.mall.platform.web.index;
import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.api.cmpt.RedisApi;
import com.dj.mall.api.product.ProductApi;
import com.dj.mall.model.base.PageResult;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.constant.SystemConstant;
import com.dj.mall.model.dto.auth.user.UserDTOResp;
import com.dj.mall.model.dto.dict.dict.DictDTOResp;
import com.dj.mall.model.dto.product.ProductDTOReq;
import com.dj.mall.model.dto.product.ProductDTOResp;
import com.dj.mall.model.util.DozerUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/index/")
public class indexController {

    @Reference
    private ProductApi productApi;

    @RequestMapping("indexProductList")
    public ResultModel indexProductList(ProductDTOReq productDTOReq, Integer minPrice, Integer maxPrice, Integer role, HttpSession session) throws Exception {
        UserDTOResp userDTOResp = (UserDTOResp) session.getAttribute(SystemConstant.USER_SESSION);
        PageResult pageResult = productApi.indexProductList(productDTOReq, minPrice, maxPrice, userDTOResp.getUserRole());
        return new ResultModel().success(PageResult.builder().list(DozerUtil.mapList(pageResult.getList(), ProductDTOResp.class)).pages(pageResult.getPages()).build());

    }








}
