package com.springatf.memberservice.api.vo;

import lombok.Builder;
import lombok.Data;

/**
 * 名称：<br>
 * 描述：<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@Data
@Builder
public class MemberVo {
    /**
     * 会员姓名
     */
    String memberName;

}
