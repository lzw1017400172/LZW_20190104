package com.lzw.constant;

public enum LoginConstant {

    DYNAMIC_PASSWORD,				//	动态密码
    RETRIEVE_PASSWORD,				//	找回密码的手机验证
	REGIN,							//	注册短信

    REGIN_CODE,                     //注册时缓存key
    DYNAMIC_CODE,                   //动态密码登录时缓存key
    RETRIEVE_CODE,                  //找回密码时缓存key

    LZW_LOGIN,                    //二维码前缀





	//区分是动态密码登录或者 本来短信注册却选择可租户登录
	//nopassword_login_web,				//web动态密码登录
	//nopassword_login_app,				//app动态密码登录
	//regin_login,						//本来短信验证注册的，然后选择租户登录









}
