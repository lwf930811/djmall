package com.dj.mall.admin.web.dict.dict;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.api.dict.dict.DictApi;
import com.dj.mall.model.constant.PermissionsCode;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.admin.web.dict
 * @ClassName: DictPageController
 * @Author: Liuwf
 * @Description: 字典实体类
 * @Date: 2020/4/4 23:22
 * @Version: 1.0
 */
@Controller
@RequestMapping("/base/dict/")
public class DictPageController {
    @Reference
    private DictApi dictApi;

    /**
     * @Description:字典list页面
     * @Author: Liuwf
     * @Date:
     * @param model:
     * @return: java.lang.String
     **/
    @RequestMapping("toList")
    @RequiresPermissions(value = PermissionsCode.DICT_MANAGE)
    public String toList(Model model) throws Exception {
        model.addAttribute("dictList", dictApi.queryDictListByPcode());
        return "dict/dict_list";
    }

    /**
     * @Description:字典数据修改
     * @Author: Liuwf
     * @Date:
     * @param code:
     * @param model:
     * @return: java.lang.String
     **/
     @RequestMapping("toUpdate")
     public String toUpdate(String code,Model model) throws Exception {
         model.addAttribute("dict", dictApi.queryDictByCode(code));
         return "dict/dict_update";
     }



}
