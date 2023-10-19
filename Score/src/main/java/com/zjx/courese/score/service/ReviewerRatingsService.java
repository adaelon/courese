package com.zjx.courese.score.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjx.common.utils.PageUtils;
import com.zjx.courese.score.entity.ReviewerRatingsEntity;

import java.util.Map;

/**
 * 
 *
 * @author zjx
 * @email zjxlijo@gmail.com
 * @date 2023-10-19 20:11:20
 */
public interface ReviewerRatingsService extends IService<ReviewerRatingsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

