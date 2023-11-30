package com.zjx.courese.work.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjx.common.utils.PageUtils;
import com.zjx.common.utils.Query;

import com.zjx.courese.work.dao.AssignmentsDao;
import com.zjx.courese.work.entity.AssignmentsEntity;
import com.zjx.courese.work.service.AssignmentsService;


@Service("assignmentsService")
public class AssignmentsServiceImpl extends ServiceImpl<AssignmentsDao, AssignmentsEntity> implements AssignmentsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AssignmentsEntity> page = this.page(
                new Query<AssignmentsEntity>().getPage(params),
                new QueryWrapper<AssignmentsEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryAssignmentsPageByCourseId(Map<String, Object> params, Integer courseId) {
        IPage<AssignmentsEntity> page = this.page(
                new Query<AssignmentsEntity>().getPage(params),
                new QueryWrapper<AssignmentsEntity>().eq("course_id", courseId)
        );

        return new PageUtils(page);
    }

}