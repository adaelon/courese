package com.zjx.courese.score.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjx.common.utils.PageUtils;
import com.zjx.common.utils.Query;

import com.zjx.courese.score.dao.InstructorRatingsDao;
import com.zjx.courese.score.entity.InstructorRatingsEntity;
import com.zjx.courese.score.service.InstructorRatingsService;


@Service("instructorRatingsService")
public class InstructorRatingsServiceImpl extends ServiceImpl<InstructorRatingsDao, InstructorRatingsEntity> implements InstructorRatingsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<InstructorRatingsEntity> page = this.page(
                new Query<InstructorRatingsEntity>().getPage(params),
                new QueryWrapper<InstructorRatingsEntity>()
        );

        return new PageUtils(page);
    }

}