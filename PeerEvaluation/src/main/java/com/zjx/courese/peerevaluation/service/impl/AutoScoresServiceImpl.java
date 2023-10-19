package com.zjx.courese.peerevaluation.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjx.common.utils.PageUtils;
import com.zjx.common.utils.Query;

import com.zjx.courese.peerevaluation.dao.AutoScoresDao;
import com.zjx.courese.peerevaluation.entity.AutoScoresEntity;
import com.zjx.courese.peerevaluation.service.AutoScoresService;


@Service("autoScoresService")
public class AutoScoresServiceImpl extends ServiceImpl<AutoScoresDao, AutoScoresEntity> implements AutoScoresService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AutoScoresEntity> page = this.page(
                new Query<AutoScoresEntity>().getPage(params),
                new QueryWrapper<AutoScoresEntity>()
        );

        return new PageUtils(page);
    }

}