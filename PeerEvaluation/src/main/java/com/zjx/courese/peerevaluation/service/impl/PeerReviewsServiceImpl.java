package com.zjx.courese.peerevaluation.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjx.common.utils.PageUtils;
import com.zjx.common.utils.Query;

import com.zjx.courese.peerevaluation.dao.PeerReviewsDao;
import com.zjx.courese.peerevaluation.entity.PeerReviewsEntity;
import com.zjx.courese.peerevaluation.service.PeerReviewsService;


@Service("peerReviewsService")
public class PeerReviewsServiceImpl extends ServiceImpl<PeerReviewsDao, PeerReviewsEntity> implements PeerReviewsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PeerReviewsEntity> page = this.page(
                new Query<PeerReviewsEntity>().getPage(params),
                new QueryWrapper<PeerReviewsEntity>()
        );

        return new PageUtils(page);
    }

}