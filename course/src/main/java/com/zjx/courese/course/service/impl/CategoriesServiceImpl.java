package com.zjx.courese.course.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjx.common.utils.PageUtils;
import com.zjx.common.utils.Query;

import com.zjx.courese.course.dao.CategoriesDao;
import com.zjx.courese.course.entity.CategoriesEntity;
import com.zjx.courese.course.service.CategoriesService;


@Service("categoriesService")
public class CategoriesServiceImpl extends ServiceImpl<CategoriesDao, CategoriesEntity> implements CategoriesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoriesEntity> page = this.page(
                new Query<CategoriesEntity>().getPage(params),
                new QueryWrapper<CategoriesEntity>()
        );

        return new PageUtils(page);
    }

}