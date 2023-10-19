package com.zjx.courese.work.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjx.common.utils.PageUtils;
import com.zjx.courese.work.entity.AssignmentCategoriesEntity;

import java.util.Map;

/**
 * 
 *
 * @author zjx
 * @email zjxlijo@gmail.com
 * @date 2023-10-19 11:07:34
 */
public interface AssignmentCategoriesService extends IService<AssignmentCategoriesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

