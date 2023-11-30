package com.zjx.courese.work.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjx.common.utils.PageUtils;
import com.zjx.common.utils.Query;

import com.zjx.courese.work.dao.SubmissionsDao;
import com.zjx.courese.work.entity.SubmissionsEntity;
import com.zjx.courese.work.service.SubmissionsService;


@Service("submissionsService")
public class SubmissionsServiceImpl extends ServiceImpl<SubmissionsDao, SubmissionsEntity> implements SubmissionsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SubmissionsEntity> page = this.page(
                new Query<SubmissionsEntity>().getPage(params),
                new QueryWrapper<SubmissionsEntity>()
        );

        return new PageUtils(page);
    }
    public PageUtils querySubAss(Map<String, Object> params,Integer assignmentId) {


        IPage<SubmissionsEntity> page = this.page(
                new Query<SubmissionsEntity>().getPage(params),
                new QueryWrapper<SubmissionsEntity>()
                        .eq("assignment_id", assignmentId)
        );
        return new PageUtils(page);
    }
}