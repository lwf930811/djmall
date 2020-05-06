package com.dj.mall.model.constant;

public interface SystemConstant {

	/**
	 * 1：未删除
	 */
	 Integer NOT_DELETE_IS_DEL = 1;

	/**
	 * 2：删除
	 */
	 Integer DELETE_IS_DEL = 2;

	/**
	 * 分页1
	 */
	 Integer PAGE_SIZE = 1;

	/**
	 * 输入不能为空
	 */
	 String NOT_NULL = "输入不能为空";

	/**
	 * 服务器异常
	 */
	 String EXCEPTION = "服务器异常";

	/**
	 * 账号或密码错误
	 */
	 String NUMBER_PWD_ERROR = "账号或密码错误";
	
	/**
	 * 该邮箱已注册
	 */
	 String EMAIL_ERROR = "该邮箱已注册";

	/**
	 * 该电话号已注册
	 */
	 String PHONE_ERROR = "该电话号已注册";

	/**
	 * 该用户名已注册
	 */
	 String NAME_ERROR = "该用户名已注册";
	
	/**
	 * 输入有误
	 */
	 String INPUT_ERROR = "输入有误";

	/**
	 * 激活
	 */
	 Integer ACTIVATE_STATUS = 2;

	
	/**
	 * 该验证码以失效，请重新获取
	 */
	 String TIME_OUT = "该验证码以失效，请重新获取";
	/**
	 * 激活邮件主题
	 */
	 String  ACTIVATE= "验证邮箱激活";

	/**
	 * 注册成功！我们已经发送了一封邮件到您的邮箱，请点击邮箱中的链接来激活您的账户！
	 */
	 String EMAIL = "注册成功！我们已经发送了一封邮件到您的邮箱，请点击邮箱中的链接来激活您的账户！";
	/**
	 * @Description:父级pid -1
	 * @Author: Liuwf
	 * @Date:
	 * @param null:
	 * @return: null
	 **/
	 Integer PARENT_ID = -1;
	/**
	 * @Description:parentName "-"
	 * @Author: Liuwf
	 * @Date:
	 * @param null:
	 * @return: null
	 **/
	 String PARENT_NAME  = "-";
	 /**
	  * @Description:用户session USER_SESSION
	  * @Author: Liuwf
	  * @Date:
	  * @param null:
	  * @return: null
	  **/
	String USER_SESSION = "USER_SESSION";
	/**
	 * @Description:用户资源USER_RESOURCE
	 * @Author: Liuwf
	 * @Date:
	 * @param null:
	 * @return: null
	 **/
	String  USER_RESOURCE = "USER_RESOURCE";
	/**
	 * @Description:用户名不存在
	 * @Author: Liuwf
	 * @Date:
	 * @param null:
	 * @return: null
	 **/
	String NULL_USERNAME  = "用户名不存在";

	/**
	 * @Description:注册成功
	 * @Author: Liuwf
	 * @Date:
	 * @param null:
	 * @return: null
	 **/
	String REGISTER_SUCCESS = "注册成功";

	/**
	 * @Description:用户已删除
	 * @Author: Liuwf
	 * @Date:
	 * @param null:
	 * @return: null
	 **/
	String USER_IS_DEL = "用户已删除";

	/**
	 * @Description:用户未激活
	 * @Author: Liuwf
	 * @Date:
	 * @param null:
	 * @return: null
	 **/
	Integer NOT_ACTIVATE = 1;

	/**
	 * @Description:用户已激活
	 * @Author: Liuwf
	 * @Date:
	 * @param null:
	 * @return: null
	 **/
	String ACTIVATE_SUCCESS = "已激活";

	/**
	 * @Description:性别父STATUS
	 * @Author: Liuwf
	 * @Date:
	 * @param null:
	 * @return: null
	 **/
	String P_CODE_SEX = "SEX";

	/**
	 * @Description:状态父STATUS
	 * @Author: Liuwf
	 * @Date:
	 * @param null:
	 * @return: null
	 **/
	String P_CODE_STATUS = "STATUS";

	/**
	 * @Description:手机号不存在，请注册
	 * @Author: Liuwf
	 * @Date:
	 * @param null:
	 * @return: null
	 **/
	String PHONE_NOT_REGISTER  = "手机号不存在，请注册";

	/**
	 * @Description:发送成功
	 * @Author: Liuwf
	 * @Date:
	 * @param null:
	 * @return: null
	 **/
	String SEND_SUCCESS = "发送成功";

	/**
	 * @Description:未激活
	 * @Author: Liuwf
	 * @Date:
	 * @param null:
	 * @return: null
	 **/
	String NOT_ACTIVATE_STATUS = "未激活";

	/**
	 * @Description:邮箱激活
	 * @Author: Liuwf
	 * @Date:
	 * @param null:
	 * @return: null
	 **/
	String NOT_ACTIVATE_SUCCESS = "请在自己邮箱中进行激活";

	/**
	 * @Description:密码修改
	 * @Author: Liuwf
	 * @Date:
	 * @param null:
	 * @return: null
	 **/
	String UPDATE_NEW_PASSWORD = "必须进行密码修改";

	/**
	 * @Description:字典父code
	 * @Author: Liuwf
	 * @Date:
	 * @param null:
	 * @return: null
	 **/
	String SYSTEM = "SYSTEM";
	
	/**
	 * @Description:重置密码登录
	 * @Author: Liuwf
	 * @Date:  
	 * @param null: 
	 * @return: null
	 **/
	Integer RESET_PASSWORD_CODE = 300;

	/**
	 * @Description:左侧菜单展示 type = 1
	 * @Author: Liuwf
	 * @Date:
	 * @param null:
	 * @return: null
	 **/
	Integer RESOURCE_TYPE = 1;
	
	/**
	 * @Description:p_code为LOGISTICS_COMPANY
	 * @Author: Liuwf
	 * @Date:  
	 * @param null: 
	 * @return: null
	 **/
	String P_CODE_LOGISTICS_COMPANY = "LOGISTICS_COMPANY";

	/**
	 * @Description:错误码
	 * @Author: Liuwf
	 * @Date:
	 * @param null:
	 * @return: null
	 **/
	Integer ERROR_CODE = -1;

	/**
	 * @Description:性别SEX
	 * @Author: Liuwf
	 * @Date:
	 * @param null:
	 * @return: null
	 **/
	String 	DICT_P_CODE_SEX_TYPE = "SEX";

	/**
	 * @Description激活状态STATUS
	 * @Author: Liuwf
	 * @Date:
	 * @param null:
	 * @return: null
	 **/
	String 	DICT_P_CODE_ACTIVE_STATUS = "STATUS";
	/**
	 * @Description:物流公司 LOGISTICS_COMPANY
	 * @Author: Liuwf
	 * @Date:
	 * @param null:
	 * @return: null
	 **/
	String 	DICT_P_CODE_LOGISTICS_COMPANY = "LOGISTICS_COMPANY";

	/**
	 * @Description:SYSTEM
	 * @Author: Liuwf
	 * @Date:
	 * @param null:
	 * @return: null
	 **/
	String 	DICT_P_CODE_SYSTEM = "SYSTEM";
	/**
	 * @Description:商品类型 PRODUCT_TYPE
	 * @Author: Liuwf
	 * @Date:
	 * @param null:
	 * @return: null
	 **/
	String 	DICT_P_CODE_PRODUCT_TYPE = "PRODUCT_TYPE";

	/**
	 * @Description:Sku不默认 0
	 * @Author: Liuwf
	 * @Date:
	 * @param null:
	 * @return: null
	 **/
	Integer SKU_NOT_DEFAULT = 0;
	/**
	 * @Description:Sku默认 1
	 * @Author: Liuwf
	 * @Date:
	 * @param null:
	 * @return: null
	 **/
	Integer SKU_IS_DEFAULT = 1;

	/**
	 * @Description:Sku下架 0
	 * @Author: Liuwf
	 * @Date:
	 * @param null:
	 * @return: null
	 **/
	Integer SKU_STATUS_SOLD_OUT = 0;

	/**
	 * @Description:Sku上架 1
	 * @Author: Liuwf
	 * @Date:
	 * @param null:
	 * @return: null
	 **/
	Integer SKU_STATUS_PUTAWAY = 1;

	/**
	 * @Description:sku集合中下标为0的
	 * @Author: Liuwf
	 * @Date:
	 * @param null:
	 * @return: null
	 **/
    Integer SKU_INDEX_IS_DEFAULT = 0;

    /**
     * @Description:最开始点赞数
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    Integer START_LIKE_NUMBER = 0;

    /**
     * @Description:最开始订单量
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
	Integer START_ORDER_NUMBER = 0;

	/**
	 * @Description:七牛云图片路径
	 * @Author: Liuwf
	 * @Date:
	 * @param null:
	 * @return: null
	 **/
	String  FRONTURL = "http://q9fdjyja1.bkt.clouddn.com/";

	/**
	 * @Description:卖家权限ID
	 * @Author: Liuwf
	 * @Date:
	 * @param null:
	 * @return: null
	 **/
	Integer USER_ROLE_ID = 3;

	/**
	 * @Description:无权限登陆
	 * @Author: Liuwf
	 * @Date:
	 * @param null:
	 * @return: null
	 **/
	String NOT_PERMISSION = "无权限登陆";

	/**
	 * @Description:
	 * @Author: Liuwf
	 * @Date:
	 * @param null:
	 * @return: null
	 **/
	String USER_TOKEN = "USER_TOKEN";
}
