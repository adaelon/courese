package com.zjx.courese.score.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjx.common.utils.PageUtils;
import com.zjx.common.utils.Query;

import com.zjx.courese.score.dao.ReviewerRatingsDao;
import com.zjx.courese.score.entity.ReviewerRatingsEntity;
import com.zjx.courese.score.service.ReviewerRatingsService;


@Service("reviewerRatingsService")
public class ReviewerRatingsServiceImpl extends ServiceImpl<ReviewerRatingsDao, ReviewerRatingsEntity> implements ReviewerRatingsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ReviewerRatingsEntity> page = this.page(
                new Query<ReviewerRatingsEntity>().getPage(params),
                new QueryWrapper<ReviewerRatingsEntity>()
        );

        return new PageUtils(page);
    }

}