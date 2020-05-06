package com.dj.mall.entity.auth.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.entity.auth.record
 * @ClassName: TimeRecordEntity
 * @Author: Liuwf
 * @Description: 时间记录实体
 * @Date: 2020/4/3 15:12
 * @Version: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("djmall_auth_time_record")
public class TimeRecordEntity implements Serializable {
    /**
     * @Description:主键id
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * @Description:用户id
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    private Integer userId;

    /**
     * @Description:用户最后登录时间
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime userEndLoginTime;



}
