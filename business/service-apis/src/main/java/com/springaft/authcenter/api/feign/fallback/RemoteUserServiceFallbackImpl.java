//package com.springaft.admin.api.feign.fallback;
//
//import com.springaft.admin.api.dto.UserInfo;
//import RemoteUserService;
//import com.springaft.common.respbase.BaseApiService;
//import com.springaft.common.respbase.ResponseResult;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
///**
// * 名称：<br>
// * 描述：<br>
// *
// * @author JeffDu
// * @version 1.0
// * @since 1.0.0
// */
//@Slf4j
//@Component
//public class RemoteUserServiceFallbackImpl extends BaseApiService implements RemoteUserService {
//
//    @Setter
//    private Throwable cause;
//
//    /**
//     * 通过用户名查询用户、角色信息
//     *
//     * @param username 用户名
//     * @param from     内外标志
//     * @return R
//     */
//    @Override
//    public ResponseResult<UserInfo> info(String username) {
//        log.error("feign 查询用户信息失败:{}", username, cause);
//        return bulidError(501, "查询用户信息失败，userID：" + username);
//    }
//}
