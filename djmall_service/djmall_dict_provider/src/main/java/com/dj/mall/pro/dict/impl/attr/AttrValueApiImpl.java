package com.dj.mall.pro.dict.impl.attr;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.api.dict.attr.AttrValueApi;
import com.dj.mall.entity.dict.attr.AttrEntity;
import com.dj.mall.entity.dict.attr.AttrValueEntity;
import com.dj.mall.mapper.dict.attr.AttrValueMapper;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.constant.SystemConstant;
import com.dj.mall.model.dto.dict.attr.AttrDTOReq;
import com.dj.mall.model.dto.dict.attr.AttrValueDTOReq;
import com.dj.mall.model.dto.dict.attr.AttrValueDTOResp;
import com.dj.mall.model.util.DozerUtil;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;


/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.pro.dict.impl.attr
 * @ClassName: AttrValueApiImpl
 * @Author: Liuwf
 * @Description: 属性值
 * @Date: 2020/4/11 22:07
 * @Version: 1.0
 */
@Service
public class AttrValueApiImpl extends ServiceImpl<AttrValueMapper, AttrValueEntity> implements AttrValueApi {


    /**
     * @param attrValueDTOReq :
     * @Description:属性值新增
     * @Author: Liuwf
     * @Date:
     * @return: void
     **/
    @Override
    public void addAttrValue(AttrValueDTOReq attrValueDTOReq) throws Exception, BusinessException {
        if (StringUtils.isEmpty(attrValueDTOReq.getAttrValue()) || null == attrValueDTOReq){
            throw new BusinessException(SystemConstant.ERROR_CODE,SystemConstant.NOT_NULL);
        }
        this.save(DozerUtil.map(attrValueDTOReq,AttrValueEntity.class));
    }

    /**
     * @param attrId :
     * @Description:根据属性查找属性集合
     * @Author: Liuwf
     * @Date:
     * @return: java.util.List<com.dj.mall.model.dto.dict.attr.AttrValueDTOResp>
     **/
    @Override
    public List<AttrValueDTOResp> queryValueByAttrId(Integer attrId) throws Exception, BusinessException {
        QueryWrapper<AttrValueEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("attr_id",attrId);
        return DozerUtil.mapList(this.list(wrapper),AttrValueDTOResp.class);
    }

    /**
     * @param id :
     * @Description:删除属性值
     * @Author: Liuwf
     * @Date:
     * @return: void
     **/
    @Override
    public void removeValue(Integer id) throws Exception, BusinessException {
        this.removeById(id);
    }
}
