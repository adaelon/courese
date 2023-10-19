package com.zjx.courese.course.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjx.common.utils.PageUtils;
import com.zjx.common.utils.Query;

import com.zjx.courese.course.dao.CoursesDao;
import com.zjx.courese.course.entity.CoursesEntity;
import com.zjx.courese.course.service.CoursesService;


@Service("coursesService")
public class CoursesServiceImpl extends ServiceImpl<CoursesDao, CoursesEntity> implements CoursesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CoursesEntity> page = this.page(
                new Query<CoursesEntity>().getPage(params),
                new QueryWrapper<CoursesEntity>()
        );

        return new PageUtils(page);
    }

}