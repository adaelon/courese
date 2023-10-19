package com.zjx.courese.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjx.common.utils.PageUtils;
import com.zjx.courese.user.entity.UserRolesEntity;

import java.util.Map;

/**
 * 
 *
 * @author zjx
 * @email zjxlijo@gmail.com
 * @date 2023-10-19 20:18:23
 */
public interface UserRolesService extends IService<UserRolesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

