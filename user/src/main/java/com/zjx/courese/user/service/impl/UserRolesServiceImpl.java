package com.zjx.courese.user.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjx.common.utils.PageUtils;
import com.zjx.common.utils.Query;

import com.zjx.courese.user.dao.UserRolesDao;
import com.zjx.courese.user.entity.UserRolesEntity;
import com.zjx.courese.user.service.UserRolesService;


@Service("userRolesService")
public class UserRolesServiceImpl extends ServiceImpl<UserRolesDao, UserRolesEntity> implements UserRolesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserRolesEntity> page = this.page(
                new Query<UserRolesEntity>().getPage(params),
                new QueryWrapper<UserRolesEntity>()
        );

        return new PageUtils(page);
    }

}