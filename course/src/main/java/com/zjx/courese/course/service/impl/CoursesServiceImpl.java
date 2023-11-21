package com.zjx.courese.course.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

       @Override
       public PageUtils queryPageLimit(Map<String, Object> params){
           Map<Integer, String> courseLevelMap = new HashMap<>();
           courseLevelMap.put(0, "入门");
           courseLevelMap.put(1, "初级");
           courseLevelMap.put(2, "中级");
           courseLevelMap.put(3, "高级");

           String courseLevelParam = (String) params.get("courseLevel");
           Integer courseLevel = null;
           if (StringUtils.isNotBlank(courseLevelParam)) {
               courseLevel = Integer.parseInt(courseLevelParam);
           }

           String orderByField = null;
           Boolean isAsc = false;
           if ("time".equals(params.get("creationDate"))) {
               orderByField = "creation_date";  // 根据时间排序
               isAsc = false;  // 降序排序
           } else if ("persons".equals(params.get("creationDate"))) {
               orderByField = "course_people";  // 根据人数排序
               isAsc = false;  // 降序排序
           }

           IPage<CoursesEntity> page = this.page(
                   new Query<CoursesEntity>().getPage(params),
                   new QueryWrapper<CoursesEntity>()
                           .eq(StringUtils.isNotBlank((CharSequence) params.get("categoryId")), "category_id", params.get("categoryId"))
                           .apply(courseLevel != null, "course_level = {0}", courseLevel != null ? courseLevelMap.get(courseLevel) : null)
                           .like(StringUtils.isNotBlank((CharSequence) params.get("courseName")),"course_name",params.get("courseName"))
                           .last(orderByField != null, "ORDER BY " + orderByField + (isAsc ? " ASC" : " DESC"))
                   );


        return new PageUtils(page);
    };


    @Override
    public PageUtils queryTeacherCourse(Map<String, Object> params){
        Object userIdObj = params.get("userId");
        Integer userId = null;

        if (userIdObj instanceof Integer) {
            userId = (Integer) userIdObj;
        } else if (userIdObj instanceof String) {
            userId = Integer.parseInt((String) userIdObj);
        }

        IPage<CoursesEntity> page = this.page(
                new Query<CoursesEntity>().getPage(params),
                new QueryWrapper<CoursesEntity>().eq("creator_id",userId)
        );

        return new PageUtils(page);
    }

}