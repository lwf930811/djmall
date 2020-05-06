package com.dj.mall.admin.web.dict.express;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.dict.express.ExpressVOResp;
import com.dj.mall.api.dict.dict.DictApi;
import com.dj.mall.api.dict.express.ExpressApi;
import com.dj.mall.model.constant.PermissionsCode;
import com.dj.mall.model.dto.dict.express.ExpressDTOResp;
import com.dj.mall.model.util.DozerUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.admin.web.dict.express
 * @ClassName: ExpressPageController
 * @Author: Liuwf
 * @Description: 物流运费视图
 * @Date: 2020/4/11 12:38
 * @Version: 1.0
 */
@Controller
@RequestMapping("/dict/express")
public class ExpressPageController {
    @Reference
    private DictApi dictApi;
    @Reference
    private ExpressApi expressApi;
    /**
     * @Description:物流list页面
     * @Author: Liuwf
     * @Date:
     * @param model:
     * @return: java.lang.String
     **/
    @RequestMapping("toList")
    @RequiresPermissions(value = PermissionsCode.DICT_MANAGE)
    public String toExpressList(Model model) throws Exception {
        model.addAttribute("dictList", dictApi.queryLogisticsListByPcode());
        return "dict/express/express_transportation_list";
    }

    /**
     * @Description:修改页面
     * @Author: Liuwf
     * @Date:
     * @param id:
     * @param model:
     * @return: java.lang.String
     **/
    @RequestMapping("toUpdate")
    public String updateExpressList(Integer id,Model model) throws Exception {
        model.addAttribute("exp", DozerUtil.map(expressApi.findExpressById(id), ExpressVOResp.class));
        return "dict/express/express_transportation_update";
    }



}
