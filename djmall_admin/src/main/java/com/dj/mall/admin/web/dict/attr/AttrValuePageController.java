package com.dj.mall.admin.web.dict.attr;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.dict.attr.AttrVOResp;
import com.dj.mall.api.dict.attr.AttrApi;
import com.dj.mall.model.dto.dict.attr.AttrDTOResp;
import com.dj.mall.model.util.DozerUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.admin.web.dict.attr
 * @ClassName: AttrValuePageController
 * @Author: Liuwf
 * @Description: 属性值视图
 * @Date: 2020/4/12 8:43
 * @Version: 1.0
 */
@Controller
@RequestMapping("/dict/value/")
public class AttrValuePageController {
    @Reference
    private AttrApi attrApi;
    /**
     * @Description:属性值展示
     * @Author: Liuwf
     * @Date:
     * @param id:
     * @param model:
     * @return: java.lang.String
     **/
    @RequestMapping("toList")
    public String toList(Integer id, Model model) throws Exception {
        AttrDTOResp attr = attrApi.findAttrById(id);
        model.addAttribute("attr", DozerUtil.map(attr, AttrVOResp.class));
        return "dict/attr/attr_value_list";
    }


}
