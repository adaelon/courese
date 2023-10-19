package com.zjx.courese.peerevaluation.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjx.common.utils.PageUtils;
import com.zjx.common.utils.Query;

import com.zjx.courese.peerevaluation.dao.AnomaliesDao;
import com.zjx.courese.peerevaluation.entity.AnomaliesEntity;
import com.zjx.courese.peerevaluation.service.AnomaliesService;


@Service("anomaliesService")
public class AnomaliesServiceImpl extends ServiceImpl<AnomaliesDao, AnomaliesEntity> implements AnomaliesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AnomaliesEntity> page = this.page(
                new Query<AnomaliesEntity>().getPage(params),
                new QueryWrapper<AnomaliesEntity>()
        );

        return new PageUtils(page);
    }

}