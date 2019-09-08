package com.springatf.common.respbase;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

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
public class ResponseResult<T> {

	private Integer rtnCode;
	private String msg;
	private T data;

	public ResponseResult(Integer rtnCode, String msg, T data) {
		super();
		this.rtnCode = rtnCode;
		this.msg = msg;
		this.data = data;
	}
}
