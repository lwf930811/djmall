package com.dj.mall.admin.web.dict.dict;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.dict.dict.DictVOReq;
import com.dj.mall.admin.vo.dict.dict.DictVOResp;
import com.dj.mall.api.dict.dict.DictApi;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.constant.PermissionsCode;
import com.dj.mall.model.dto.dict.dict.DictDTOReq;
import com.dj.mall.model.dto.dict.dict.DictDTOResp;
import com.dj.mall.model.util.DozerUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.admin.web.dict
 * @ClassName: DictController
 * @Author: Liuwf
 * @Description: 字典实体
 * @Date: 2020/4/4 23:23
 * @Version: 1.0
 */
@RestController
@RequestMapping("/base/dict/")
public class DictController {
    @Reference
    private DictApi dictApi;

    /**
     * @Description:字典展示
     * @Author: Liuwf
     * @Date:
     * @return: com.dj.mall.model.base.ResultModel<java.lang.Object>
     **/
    @GetMapping
    @RequiresPermissions(value = PermissionsCode.DICT_MANAGE)
    public ResultModel<Object> list() throws Exception {
        List<DictDTOResp> dtoRespList = dictApi.queryDictList();
        return new ResultModel<>().success(DozerUtil.mapList(dtoRespList, DictVOResp.class));
    }

    /**
     * @Description:修改
     * @Author: Liuwf
     * @Date:

     * @return: com.dj.mall.model.base.ResultModel<java.lang.Object>
     **/
    @PutMapping
    public ResultModel<Object> updateDict(DictVOReq dictVOReq) throws Exception {
        dictApi.updateDict(DozerUtil.map(dictVOReq, DictDTOReq.class));
        return new ResultModel<>().success();
    }
    /**
     * @Description:字典新增
     * @Author: Liuwf
     * @Date:
     * @param dictVOReq:
     * @return: com.dj.mall.model.base.ResultModel<java.lang.Object>
     **/
    @PostMapping
    public ResultModel<Object> addDict(DictVOReq dictVOReq) throws Exception {
        dictApi.addDict(DozerUtil.map(dictVOReq, DictDTOReq.class));
        return new ResultModel<>().success();
    }



}
