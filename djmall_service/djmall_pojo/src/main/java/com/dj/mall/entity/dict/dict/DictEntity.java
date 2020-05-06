package com.dj.mall.entity.dict.dict;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dozer.Mapping;

import java.io.Serializable;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.entity.auth.dict
 * @ClassName: DictEntity
 * @Author: Liuwf
 * @Description: 字典实体类
 * @Date: 2020/4/3 14:58
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("djmall_auth_dict")
public class DictEntity implements Serializable {


    /**
     * @Description:字典数据编码
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String code;

    /**
     * @Description:字典数据名
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String name;

    /**
     * @Description:字典数据父级编码
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private String pCode;


}
