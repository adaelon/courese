package com.zjx.courese.score.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjx.common.utils.PageUtils;
import com.zjx.courese.score.entity.CourseRatingsEntity;

import java.util.Map;

/**
 * 
 *
 * @author zjx
 * @email zjxlijo@gmail.com
 * @date 2023-10-19 20:11:20
 */
public interface CourseRatingsService extends IService<CourseRatingsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

