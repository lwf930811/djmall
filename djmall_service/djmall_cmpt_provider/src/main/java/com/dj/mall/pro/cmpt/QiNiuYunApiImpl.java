package com.dj.mall.pro.cmpt;

import com.alibaba.dubbo.config.annotation.Service;
import com.dj.mall.api.cmpt.QiNiuYunApi;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.util.UUID;

/**
 * @Description:七牛云
 * @Author: Liuwf
 * @Date:
 * @param null:
 * @return: null
 **/
@Service
public class QiNiuYunApiImpl implements QiNiuYunApi {

    /**
     * accessKey 七牛云密钥  相当于账号
     */
    private static String AK = "gODdUahiFfRmiN_24D1zGRsYihT612gIvu_ctbVX";
    /**
     * secretKey 七牛云密钥   相当于密码
     */
    private static final String SK ="2lAW5-IjGkenyePAtIWcpEVv4Yg6y2Iq2bbDhdkg";
    /**
     * 储存空间名
     */
    private static final String BUCKET = "lwfdjmall";
    /**
     * 外链URL前缀
     */
    public static final String  FRONTURL = "http://q9fdjyja1.bkt.clouddn.com/";




    /**
     * Auth 认证账号和密码<br />
     * create 创建  <br />
     * 密钥配置 账号和密码  <br />
     */
    private static final Auth AUTH = Auth.create(AK, SK);

    /**
     * 创建上传对象
     */
    private static final UploadManager UPLOADMANAGER = new UploadManager();


    /**
     * 完成验证
     * 简单上传，使用默认策略，只需要设置上传的空间名就可以了
     * @return
     */
    public static String getUpToken() {

        // 上传域名
        return AUTH.uploadToken(BUCKET);

    }


    /**
     * 上传文件 图片
     *
     * @param bytes 文件转换为字节
     * @return
     * @throws Exception
     */
    @Override
    public String upload(byte[] bytes) throws Exception {
        // 生成令牌 避免 重复  被覆盖
        String token = UUID.randomUUID().toString().replace("-", "");

        // 调用put方法上传
        Response put = UPLOADMANAGER.put(bytes, token, getUpToken());

        // 打印返回的信息
        System.out.println(put.isOK());

        System.out.println(put.bodyString());
        return FRONTURL + token;
    }

    /**
     * 普通删除文件
     *
     * @param key 文件名
     * @throws Exception
     */
    @Override
    public void del(String key) throws Exception {
            // 实例化一个BucketManager对象        将密钥配置 放入
            BucketManager bucketManager = new BucketManager(AUTH);
            // 此处的33是去掉 ： http://ongsua0j7.bkt.clouddn.com/,剩下的key就是图片在七牛云的名称
            key = key.substring(33);
            bucketManager.delete(BUCKET, key);
            System.out.println(key);
    }
}
