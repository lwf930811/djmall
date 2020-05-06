package com.dj.mall.pro.dict.impl.dict;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.api.cmpt.RedisApi;
import com.dj.mall.api.dict.dict.DictApi;
import com.dj.mall.entity.dict.dict.DictEntity;
import com.dj.mall.mapper.dict.dict.DictMapper;
import com.dj.mall.mapper.dict.sku.SkuGmMapper;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.constant.SystemConstant;
import com.dj.mall.model.dto.dict.dict.DictDTOReq;
import com.dj.mall.model.dto.dict.dict.DictDTOResp;
import com.dj.mall.model.util.DozerUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.pro.auth.impl.dict
 * @ClassName: DictApiImpl
 * @Author: Liuwf
 * @Description: 字典接口实现
 * @Date: 2020/4/3 17:11
 * @Version: 1.0
 */
@Service
public class DictApiImpl extends ServiceImpl<DictMapper, DictEntity> implements DictApi {
    @Reference
    private RedisApi redisApi;

    @Autowired
    private SkuGmMapper skuGmMapper;

    /**
     * @param pCode :
     * @Description:根据pcode查找字典名
     * @Author: Liuwf
     * @Date:
     * @return: java.util.List<com.dj.mall.model.dto.dict.dict.DictDTOResp>
     **/
    @Override
    public List<DictDTOResp> queryDictNameByPcode(String pCode) throws Exception, BusinessException {
//        先从Redis获取，如若没有查找数据库
        List<DictDTOResp> dtoRespList = redisApi.getHashValues(pCode);
        if (null == dtoRespList || dtoRespList.size() == 0){
            QueryWrapper<DictEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("p_code", pCode);
            dtoRespList = DozerUtil.mapList(this.list(queryWrapper), DictDTOResp.class);
//            存入Redis
            dtoRespList.forEach(dict -> {
                redisApi.pushHash(dict.getPCode(),dict.getCode(),dict);
            });

        }

        return dtoRespList;
    }

    /**
     * @Description:获取所有pcode的name
     * @Author: Liuwf
     * @Date:
     * @return: java.util.List<com.dj.mall.model.dto.auth.dict.DictDTOResp>
     **/
    @Override
    public List<DictDTOResp> queryDictListByPcode() throws Exception, BusinessException {
        QueryWrapper<DictEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("p_code", SystemConstant.SYSTEM);
        return DozerUtil.mapList(this.list(queryWrapper),DictDTOResp.class);
    }

    /**
     * @Description:查询字典集合
     * @Author: Liuwf
     * @Date:
     * @return: java.util.List<com.dj.mall.model.dto.auth.dict.DictDTOResp>
     **/
    @Override
    public List<DictDTOResp> queryDictList() throws Exception, BusinessException {
        List<DictEntity> dictEntityList = this.list();
        return DozerUtil.mapList(dictEntityList,DictDTOResp.class);
    }

    /**
     * @param code :
     * @Description:根据code查找对象
     * @Author: Liuwf
     * @Date:
     * @return: com.dj.mall.model.dto.auth.dict.DictDTOResp
     **/
    @Override
    public DictDTOResp queryDictByCode(String code) throws Exception, BusinessException {
        QueryWrapper<DictEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code",code);
        return DozerUtil.map(this.getOne(queryWrapper),DictDTOResp.class);
    }


    /**
     * @param dtoReq :
     * @Description:修改字典
     * @Author: Liuwf
     * @Date:
     * @return: void
     **/
    @Override
    public void updateDict(DictDTOReq dtoReq) throws Exception, BusinessException {
        UpdateWrapper<DictEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("name",dtoReq.getName());
        updateWrapper.eq("code",dtoReq.getCode());
        this.update(updateWrapper);
        redisApi.pushHash(dtoReq.getPCode(),dtoReq.getCode(),dtoReq);
    }

    /**
     * @param dtoReq :
     * @Description:新增数据名
     * @Author: Liuwf
     * @Date:
     * @return: void
     **/
    @Override
    public void addDict(DictDTOReq dtoReq) throws Exception, BusinessException {
        dtoReq.setPCode(dtoReq.getPCode().toUpperCase());
        dtoReq.setCode(dtoReq.getCode().toUpperCase());
        this.save(DozerUtil.map(dtoReq, DictEntity.class));
        redisApi.pushHash(dtoReq.getPCode(),dtoReq.getCode(),dtoReq);

    }

    /**
     * @param
     * @Description:根据pcode为LOGISTICS_COMPANY查物流公司
     * @Author: Liuwf
     * @Date:
     * @return: java.util.List<com.dj.mall.model.dto.dict.DictDTOResp>
     **/
    @Override
    public List<DictDTOResp> queryLogisticsListByPcode() throws Exception, BusinessException {
        QueryWrapper<DictEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("p_code", SystemConstant.P_CODE_LOGISTICS_COMPANY);
        return DozerUtil.mapList(this.list(queryWrapper),DictDTOResp.class);
    }
}
