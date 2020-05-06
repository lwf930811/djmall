package com.dj.mall.pro.dict.impl.express;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.api.dict.express.ExpressApi;
import com.dj.mall.entity.dict.express.ExpressEntity;
import com.dj.mall.mapper.dict.express.ExpressMapper;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.dto.dict.express.ExpressDTOReq;
import com.dj.mall.model.dto.dict.express.ExpressDTOResp;
import com.dj.mall.model.util.DozerUtil;

import java.util.List;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.pro.dict.impl
 * @ClassName: ExpressApiImpl
 * @Author: Liuwf
 * @Description: 运费是实现类
 * @Date: 2020/4/11 12:10
 * @Version: 1.0
 */
@Service
public class ExpressApiImpl extends ServiceImpl<ExpressMapper, ExpressEntity> implements ExpressApi {


    /**
     * @param expressDTOReq :
     * @Description:新增运费
     * @Author: Liuwf
     * @Date:
     * @return: void
     **/
    @Override
    public void addExpress(ExpressDTOReq expressDTOReq) throws Exception, BusinessException {
        this.save(DozerUtil.map(expressDTOReq,ExpressEntity.class));
    }

    /**
     * @Description:查询物流信息
     * @Author: Liuwf
     * @Date:
     * @return: java.util.List<com.dj.mall.model.dto.dict.express.ExpressDTOResp>
     **/
    @Override
    public List<ExpressDTOResp> queryExpress() throws Exception, BusinessException {
        List<ExpressDTOResp> expressDTOResps = getBaseMapper().queryExpress();
        return expressDTOResps;
    }

    /**
     * @param id :
     * @Description:查询物流
     * @Author: Liuwf
     * @Date:
     * @return: com.dj.mall.model.dto.dict.express.ExpressDTOResp
     **/
    @Override
    public ExpressDTOResp findExpressById(Integer id) throws Exception, BusinessException {
        return DozerUtil.map(this.getById(id),ExpressDTOResp.class);
    }

    /**
     * @param expressDTOReq :
     * @Description:修改
     * @Author: Liuwf
     * @Date:
     * @return: void
     **/
    @Override
    public void updateExpress(ExpressDTOReq expressDTOReq) throws Exception, BusinessException {
        UpdateWrapper<ExpressEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("expressage",expressDTOReq.getExpressage());
        updateWrapper.eq("id",expressDTOReq.getExpressId());
        this.update(updateWrapper);
    }
}
