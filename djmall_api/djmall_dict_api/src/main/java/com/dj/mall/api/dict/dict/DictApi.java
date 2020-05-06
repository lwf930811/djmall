package com.dj.mall.api.dict.dict;

import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.dto.dict.dict.DictDTOReq;
import com.dj.mall.model.dto.dict.dict.DictDTOResp;

import java.util.List;


public interface DictApi {

    /**
     * @Description:根据pcode查找字典名
     * @Author: Liuwf
     * @Date:
     * @param pCode:
     * @return: java.util.List<com.dj.mall.model.dto.dict.dict.DictDTOResp>
     **/
    List<DictDTOResp> queryDictNameByPcode(String pCode) throws Exception, BusinessException;

    /**
     * @Description:获取所有pcode的name
     * @Author: Liuwf
     * @Date:

     * @return: java.util.List<com.dj.mall.model.dto.auth.dict.DictDTOResp>
     **/
    List<DictDTOResp> queryDictListByPcode() throws Exception, BusinessException;

    /**
     * @Description:查询字典集合
     * @Author: Liuwf
     * @Date:

     * @return: java.util.List<com.dj.mall.model.dto.auth.dict.DictDTOResp>
     **/
    List<DictDTOResp> queryDictList() throws Exception,BusinessException;

    /**
     * @Description:根据code查找对象
     * @Author: Liuwf
     * @Date:
     * @param code :
     * @return: com.dj.mall.model.dto.auth.dict.DictDTOResp
     **/
    DictDTOResp queryDictByCode(String code) throws Exception,BusinessException;

    /**
     * @Description:修改字典
     * @Author: Liuwf
     * @Date:
     * @param dtoReq:
     * @return: void
     **/
    void updateDict(DictDTOReq dtoReq) throws Exception, BusinessException;

    /**
     * @Description:新增数据名
     * @Author: Liuwf
     * @Date:
     * @param dtoReq:
     * @return: void
     **/
    void addDict(DictDTOReq dtoReq) throws Exception,BusinessException;

    /**
     * @Description:根据pcode为LOGISTICS_COMPANY查物流公司
     * @Author: Liuwf
     * @Date:
     * @param
     * @return: java.util.List<com.dj.mall.model.dto.dict.DictDTOResp>
     **/
    List<DictDTOResp> queryLogisticsListByPcode() throws Exception, BusinessException;






}
