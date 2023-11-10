package com.zjx.courese.course.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjx.common.utils.PageUtils;
import com.zjx.courese.course.entity.CourseSubscriptionsEntity;

import java.util.Map;

/**
 * 
 *
 * @author zjx
 * @email zjxlijo@gmail.com
 * @date 2023-10-19 19:53:44
 */
public interface CourseSubscriptionsService extends IService<CourseSubscriptionsEntity> {

    PageUtils queryPage(Map<String, Object> params);


    PageUtils queryUserCourse(Map<String, Object> params);
}

