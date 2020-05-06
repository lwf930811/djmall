package com.dj.mall.mapper.dict.sku;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.mall.bo.dict.sku.SkuGmBOResp;
import com.dj.mall.entity.dict.sku.SkuGmEntity;
import com.dj.mall.model.dto.dict.sku.SkuGmDTOResp;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface SkuGmMapper extends BaseMapper<SkuGmEntity> {

    List<SkuGmDTOResp> querySkuList() throws DataAccessException;
}
