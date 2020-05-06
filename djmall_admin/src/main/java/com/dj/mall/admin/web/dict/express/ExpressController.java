package com.dj.mall.admin.web.dict.express;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.dict.dict.DictVOReq;
import com.dj.mall.admin.vo.dict.express.ExpressVOReq;
import com.dj.mall.api.dict.dict.DictApi;
import com.dj.mall.api.dict.express.ExpressApi;
import com.dj.mall.bo.dict.express.ExpressBOResp;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.dto.dict.dict.DictDTOReq;
import com.dj.mall.model.dto.dict.express.ExpressDTOReq;
import com.dj.mall.model.dto.dict.express.ExpressDTOResp;
import com.dj.mall.model.util.DozerUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.admin.web.dict.express
 * @ClassName: ExpressController
 * @Author: Liuwf
 * @Description: 物流运费实体
 * @Date: 2020/4/11 12:39
 * @Version: 1.0
 */
@RestController
@RequestMapping("/dict/express/")
public class ExpressController {
    @Reference
    private ExpressApi expressApi;

    /**
     * @Description:新增物流
     * @Author: Liuwf
     * @Date:
     * @param expressVOReq:
     * @return: com.dj.mall.model.base.ResultModel<java.lang.Object>
     **/
    @PostMapping
    public ResultModel<Object> addExpress(ExpressVOReq expressVOReq) throws Exception {
        expressApi.addExpress(DozerUtil.map(expressVOReq, ExpressDTOReq.class));
        return new ResultModel<>().success();
    }

    /**
     * @Description:获取全部物流信息
     * @Author: Liuwf
     * @Date:
     * @return: com.dj.mall.model.base.ResultModel<java.lang.Object>
     **/
    @GetMapping
    public ResultModel<Object> findExpressList() throws Exception {
        List<ExpressDTOResp> expressDTOResps = expressApi.queryExpress();
        return new ResultModel<>().success(DozerUtil.mapList(expressDTOResps, ExpressBOResp.class));
    }

    /**
     * @Description:修改
     * @Author: Liuwf
     * @Date:
     * @param expressVOReq:
     * @return: com.dj.mall.model.base.ResultModel<java.lang.Object>
     **/
    @PutMapping
    public ResultModel<Object> updateExpressList(ExpressVOReq expressVOReq) throws Exception {
        expressApi.updateExpress(DozerUtil.map(expressVOReq,ExpressDTOReq.class));
        return new ResultModel<>().success();
    }
}
