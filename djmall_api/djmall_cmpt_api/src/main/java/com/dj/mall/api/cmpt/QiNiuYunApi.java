package com.dj.mall.api.cmpt;

/**
 * @Description:七牛云
 * @Author: Liuwf
 * @Date:
 * @param null:
 * @return: null
 **/
public interface QiNiuYunApi {
    /**
     * 上传
     * @param bytes
     * @return
     * @throws Exception
     */
    String upload(byte[] bytes) throws Exception;
    /**
     * 删除
     * @param key
     * @throws Exception
     */
    void del(String key) throws Exception;
}
