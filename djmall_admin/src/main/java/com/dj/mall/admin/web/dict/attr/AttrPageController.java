package com.dj.mall.admin.web.dict.attr;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.api.dict.attr.AttrApi;
import com.dj.mall.model.constant.PermissionsCode;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.admin.web.dict.attr
 * @ClassName: AttrPageController
 * @Author: Liuwf
 * @Description: 属性视图
 * @Date: 2020/4/11 23:16
 * @Version: 1.0
 */
@Controller
@RequestMapping("/dict/attr/")
public class AttrPageController {

    /**
     * @Description:属性展示
     * @Author: Liuwf
     * @Date:
     * @return: java.lang.String
     **/
    @RequestMapping("toList")
    public String toList( ) throws Exception {
        return "dict/attr/attr_list";
    }
    /**
     * @Description:属性展示
     * @Author: Liuwf
     * @Date:
     * @param id:
     * @return: java.lang.String
     **/
    @RequestMapping("toAttrValue")
    public String toAttrValue(Integer id) throws Exception {
        return "dict/attr/attr_value_list";
    }


}
