package com.zjx.courese.score.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjx.common.utils.PageUtils;
import com.zjx.common.utils.Query;

import com.zjx.courese.score.dao.CourseRatingsDao;
import com.zjx.courese.score.entity.CourseRatingsEntity;
import com.zjx.courese.score.service.CourseRatingsService;


@Service("courseRatingsService")
public class CourseRatingsServiceImpl extends ServiceImpl<CourseRatingsDao, CourseRatingsEntity> implements CourseRatingsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CourseRatingsEntity> page = this.page(
                new Query<CourseRatingsEntity>().getPage(params),
                new QueryWrapper<CourseRatingsEntity>()
        );

        return new PageUtils(page);
    }

}