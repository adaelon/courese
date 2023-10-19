package com.zjx.courese.course.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjx.common.utils.PageUtils;
import com.zjx.courese.course.entity.CategoriesEntity;

import java.util.Map;

/**
 * 
 *
 * @author zjx
 * @email zjxlijo@gmail.com
 * @date 2023-10-19 19:53:45
 */
public interface CategoriesService extends IService<CategoriesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

