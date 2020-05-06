package com.dj.mall.admin.web.dict.attr;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.dict.attr.AttrVOReq;
import com.dj.mall.admin.vo.dict.attr.AttrValueVOReq;
import com.dj.mall.admin.vo.dict.attr.AttrValueVOResp;
import com.dj.mall.api.dict.attr.AttrValueApi;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.dto.dict.attr.AttrDTOReq;
import com.dj.mall.model.dto.dict.attr.AttrValueDTOReq;
import com.dj.mall.model.dto.dict.attr.AttrValueDTOResp;
import com.dj.mall.model.util.DozerUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.admin.web.dict.attr
 * @ClassName: AttrValueController
 * @Author: Liuwf
 * @Description: 属性值
 * @Date: 2020/4/12 8:41
 * @Version: 1.0
 */
@RestController
@RequestMapping("/dict/value/")
public class AttrValueController {
    @Reference
    private AttrValueApi attrValueApi;

    /**
     * @Description:
     * @Author: Liuwf
     * @Date:  
     * @param attrValueVOReq: 
     * @return: com.dj.mall.model.base.ResultModel
     **/
    @RequestMapping("addValue")
    public ResultModel addValue(AttrValueVOReq attrValueVOReq) throws Exception{
        attrValueApi.addAttrValue(DozerUtil.map(attrValueVOReq, AttrValueDTOReq.class));
        return new ResultModel().success(true);
    }

    /**
     * @Description:属性值展示
     * @Author: Liuwf
     * @Date:
     * @param attrId:
     * @return: com.dj.mall.model.base.ResultModel
     **/
    @RequestMapping("list")
    public ResultModel valueList(Integer attrId) throws Exception{
        return new ResultModel().success(DozerUtil.mapList(attrValueApi.queryValueByAttrId(attrId), AttrValueVOResp.class));

    }

    /**
     * @Description:删除属性值
     * @Author: Liuwf
     * @Date:
     * @param id:
     * @return: com.dj.mall.model.base.ResultModel
     **/
    @RequestMapping("delValue")
    public ResultModel delValue(Integer id) throws Exception{
        attrValueApi.removeValue(id);
        return new ResultModel().success();

    }


}
