package com.zjx.courese.course.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjx.common.utils.PageUtils;
import com.zjx.courese.course.entity.CoursesEntity;

import java.util.Map;

/**
 * 
 *
 * @author zjx
 * @email zjxlijo@gmail.com
 * @date 2023-10-19 19:53:45
 */
public interface CoursesService extends IService<CoursesEntity> {

    PageUtils queryPage(Map<String, Object> params);
    PageUtils queryPageLimit(Map<String, Object> params);

    PageUtils queryTeacherCourse(Map<String, Object> params);
}

