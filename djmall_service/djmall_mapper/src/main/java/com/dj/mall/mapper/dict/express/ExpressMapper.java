package com.dj.mall.mapper.dict.express;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.mall.entity.dict.express.ExpressEntity;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.dto.dict.express.ExpressDTOResp;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ExpressMapper extends BaseMapper<ExpressEntity> {

    List<ExpressDTOResp> queryExpress()throws DataAccessException;
}
