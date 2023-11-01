package com.zjx.courese.user.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjx.common.utils.PageUtils;
import com.zjx.common.utils.Query;

import com.zjx.courese.user.dao.UsersDao;
import com.zjx.courese.user.entity.UsersEntity;
import com.zjx.courese.user.service.UsersService;


@Service("usersService")
public class UsersServiceImpl extends ServiceImpl<UsersDao, UsersEntity> implements UsersService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UsersEntity> page = this.page(
                new Query<UsersEntity>().getPage(params),
                new QueryWrapper<UsersEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public UsersEntity getByName(String username) {
        UsersDao usersDao = this.baseMapper;
        UsersEntity usersEntity = usersDao.selectOne(new QueryWrapper<UsersEntity>().eq("username",username));
        if(usersEntity==null){
            return  null;
        }else {
            return usersEntity;
        }


    }

}