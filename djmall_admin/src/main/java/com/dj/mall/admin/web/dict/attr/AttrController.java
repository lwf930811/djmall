package com.dj.mall.admin.web.dict.attr;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.dict.attr.AttrVOReq;
import com.dj.mall.api.dict.attr.AttrApi;
import com.dj.mall.bo.dict.attr.AttrBOResp;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.dto.dict.attr.AttrDTOReq;
import com.dj.mall.model.dto.dict.attr.AttrDTOResp;
import com.dj.mall.model.util.DozerUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.border.StrokeBorder;
import java.util.List;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.admin.web.dict
 * @ClassName: AttrController
 * @Author: Liuwf
 * @Description: 属性视图
 * @Date: 2020/4/11 22:47
 * @Version: 1.0
 */
@RestController
@RequestMapping("/dict/attr/")
public class AttrController {
    @Reference
    private AttrApi attrApi;

    /**
     * @Description:属性名去重
     * @Author: Liuwf
     * @Date:
     * @param attrName:
     * @return: boolean
     **/
    @RequestMapping("findAttrName")
    public boolean findAttrName(String attrName) throws Exception{
        Boolean attrName1 = attrApi.findAttrName(attrName);
        return attrName1;
    }

    /**
     * @Description:查询全部属性
     * @Author: Liuwf
     * @Date:
     * @return: com.dj.mall.model.base.ResultModel
     **/
    @RequestMapping("list")
    public ResultModel attrList( ) throws Exception{
        List<AttrDTOResp> attrDTOResps = attrApi.queryAttrList();
        return new ResultModel().success(DozerUtil.mapList(attrDTOResps, AttrBOResp.class));
    }

    /**
     * @Description:新增属性
     * @Author: Liuwf
     * @Date:
     * @param attrVOReq:
     * @return: com.dj.mall.model.base.ResultModel
     **/
    @RequestMapping("add")
    public ResultModel addAttr(AttrVOReq attrVOReq) throws Exception{
        attrApi.addAttr(DozerUtil.map(attrVOReq, AttrDTOReq.class));
        return new ResultModel().success();
    }


}
