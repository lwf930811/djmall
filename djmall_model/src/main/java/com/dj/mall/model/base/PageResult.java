package com.dj.mall.model.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PageResult implements Serializable {

    /**
     * 总页数
     */
    private Long pages;

    /**
     * 数据
     */
    private List<?> list;


}
