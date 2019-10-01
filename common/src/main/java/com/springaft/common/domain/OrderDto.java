package com.springaft.common.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 名称：<br>
 * 描述：<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@Data
public class OrderDto implements Serializable {
    /**
     * 订单编号
     */
    String orderId;

}
