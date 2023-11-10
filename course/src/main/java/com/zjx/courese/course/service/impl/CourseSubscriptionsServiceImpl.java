package com.zjx.courese.course.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.zjx.courese.course.entity.CoursesEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjx.common.utils.PageUtils;
import com.zjx.common.utils.Query;

import com.zjx.courese.course.dao.CourseSubscriptionsDao;
import com.zjx.courese.course.entity.CourseSubscriptionsEntity;
import com.zjx.courese.course.service.CourseSubscriptionsService;


@Service("courseSubscriptionsService")
public class CourseSubscriptionsServiceImpl extends ServiceImpl<CourseSubscriptionsDao, CourseSubscriptionsEntity> implements CourseSubscriptionsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CourseSubscriptionsEntity> page = this.page(
                new Query<CourseSubscriptionsEntity>().getPage(params),
                new QueryWrapper<CourseSubscriptionsEntity>()
        );

        return new PageUtils(page);
    }

    public PageUtils queryUserCourse(Map<String, Object> params){
        String userNum = (String) params.get("userId");
        Integer userId = Integer.parseInt(userNum);

        IPage<CourseSubscriptionsEntity> page = this.page(
                new Query<CourseSubscriptionsEntity>().getPage(params),
                new QueryWrapper<CourseSubscriptionsEntity>()
                        .eq("user_id",userId)
        );
        return new PageUtils(page);

    }

}