package com.zjx.courese.peerevaluation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjx.common.utils.PageUtils;
import com.zjx.courese.peerevaluation.entity.AutoScoresEntity;

import java.util.Map;

/**
 * 
 *
 * @author zjx
 * @email zjxlijo@gmail.com
 * @date 2023-10-19 20:06:29
 */
public interface AutoScoresService extends IService<AutoScoresEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

