package com.springaft.common.domain;

import lombok.Builder;
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
public class MemberDto implements Serializable{

    /**
     * 会员姓名
     */
    private String memberName;
}
