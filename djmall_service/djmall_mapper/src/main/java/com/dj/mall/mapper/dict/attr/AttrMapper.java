package com.dj.mall.mapper.dict.attr;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.mall.bo.dict.attr.AttrBOResp;
import com.dj.mall.entity.dict.attr.AttrEntity;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.dto.dict.attr.AttrDTOResp;
import com.dj.mall.model.dto.dict.attr.AttrValueDTOResp;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface AttrMapper extends BaseMapper<AttrEntity> {

    List<AttrDTOResp> queryAttrList() throws DataAccessException;

    List<AttrBOResp> queryProductSkuAttrByProductType(String productType) throws DataAccessException;
}
