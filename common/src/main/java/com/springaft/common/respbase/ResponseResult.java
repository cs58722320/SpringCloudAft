package com.springaft.common.respbase;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 名称：通用返回结果对象<br>
 * 描述：通用返回结果对象<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@Data
@Slf4j
@Builder
public class ResponseResult<T> implements Serializable {

	private Integer rtnCode;
	private String msg;
	private T data;

	public ResponseResult(){

	}

	public ResponseResult(Integer rtnCode, String msg, T data) {
		super();
		this.rtnCode = rtnCode;
		this.msg = msg;
		this.data = data;
	}
}
