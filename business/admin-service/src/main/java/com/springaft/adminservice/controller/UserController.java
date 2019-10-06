package com.springaft.adminservice.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.springaft.admin.api.dto.UserInfo;
import com.springaft.admin.api.entity.SysUserEntity;
import com.springaft.adminservice.service.SysUserService;
import com.springaft.common.annotation.Inner;
import com.springaft.common.respbase.BaseApiService;
import com.springaft.common.respbase.ResponseResult;
import lombok.AllArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 名称：系统用户控制层<br>
 * 描述：系统用户控制层<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController  extends BaseApiService {

    private final SysUserService userService;

    /**
     * 获取指定用户全部信息
     *
     * @return 用户信息
     */
    @RequestMapping(path = "/info/{username}", method = RequestMethod.GET)
    public ResponseResult<UserInfo> info(@PathVariable("username") String username) {
        SysUserEntity user = userService.getOne(Wrappers.<SysUserEntity>query()
                .lambda().eq(SysUserEntity::getUsername, username));
        if (ObjectUtils.isEmpty(user)) {
            return bulidError("用户信息为空");
        }
        ResponseResult<UserInfo> result = new ResponseResult<>();
        result.setData(userService.getUserInfo(user));
        result.setRtnCode(200);
        result.setMsg("成功");
        return result;
    }


}
