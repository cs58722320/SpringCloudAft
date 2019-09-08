package com.springatf.common.respbase;


import com.springatf.common.constant.ResultConstant;

/**
 * 名称：返回结果通用处理类<br>
 * 描述：构建请求返回所需要的各种构建方式<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
public class BaseApiService {

	/**
	 * 构建错误返回对象
	 * @param code 返回码
	 * @param msg 返回消息
	 * @return
	 */
	public ResponseResult bulidError(Integer code, String msg) {
		return setResult(code, msg, null);
	}

	/**
	 * 构建错误返回对象
	 * 默认返回码为：500
	 * 返沪默认未定的业务错误
	 * @param msg 返回消息
	 * @return
	 */
	public ResponseResult bulidError(String msg) {
		return setResult(ResultConstant.HTTP_RES_CODE_500, msg, null);
	}

	/**
	 * 构建成功返回对象
	 * 默认返回码为：200
	 * 返回默认成功
	 * @param data 返回对象
	 * @return
	 */
	public ResponseResult buildSuccessWithData(Object data) {
		return setResult(ResultConstant.HTTP_RES_CODE_200, ResultConstant.HTTP_RES_CODE_200_VALUE, data);
	}

	/**
	 * 构建成功返回对象
	 * @param code 返回码
	 * @param data 返回对象
	 * @return
	 */
	public ResponseResult buildSuccess(Integer code, Object data) {
		return setResult(code, ResultConstant.HTTP_RES_CODE_200_VALUE, data);
	}

	/**
	 * 构建成功返回对象
	 * 无参构建
	 * @return
	 */
	public ResponseResult buildSuccess() {
		return setResult(ResultConstant.HTTP_RES_CODE_200, ResultConstant.HTTP_RES_CODE_200_VALUE, null);
	}

	/**
	 * 构建成功返回对象
	 * @param msg 返回消息
	 * @return
	 */
	public ResponseResult buildSuccess(String msg) {
		return setResult(ResultConstant.HTTP_RES_CODE_200, msg, null);
	}

	/**
	 * 通用封装
	 * @param code
	 * @param msg
	 * @param data
	 * @return
	 */
	public ResponseResult setResult(Integer code, String msg, Object data) {
		return new ResponseResult(code, msg, data);
	}

}
