package com.dj.mall.api.dict.express;

import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.dto.dict.express.ExpressDTOReq;
import com.dj.mall.model.dto.dict.express.ExpressDTOResp;

import java.util.List;

public interface ExpressApi {
    /**
     * @Description:新增运费
     * @Author: Liuwf
     * @Date:
     * @param expressDTOReq:
     * @return: void
     **/
    void addExpress(ExpressDTOReq expressDTOReq) throws Exception, BusinessException;

    /**
     * @Description:查询物流信息
     * @Author: Liuwf
     * @Date:

     * @return: java.util.List<com.dj.mall.model.dto.dict.express.ExpressDTOResp>
     **/
    List<ExpressDTOResp> queryExpress()throws Exception, BusinessException;

    /**
     * @Description:查询物流
     * @Author: Liuwf
     * @Date:
     * @param id:
     * @return: com.dj.mall.model.dto.dict.express.ExpressDTOResp
     **/
    ExpressDTOResp findExpressById(Integer id) throws Exception,BusinessException;

    /**
     * @Description:修改
     * @Author: Liuwf
     * @Date:
     * @param expressDTOReq:
     * @return: void
     **/
    void updateExpress(ExpressDTOReq expressDTOReq) throws Exception,BusinessException;

}
