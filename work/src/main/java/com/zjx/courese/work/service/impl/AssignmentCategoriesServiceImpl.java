package com.zjx.courese.work.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjx.common.utils.PageUtils;
import com.zjx.common.utils.Query;

import com.zjx.courese.work.dao.AssignmentCategoriesDao;
import com.zjx.courese.work.entity.AssignmentCategoriesEntity;
import com.zjx.courese.work.service.AssignmentCategoriesService;


@Service("assignmentCategoriesService")
public class AssignmentCategoriesServiceImpl extends ServiceImpl<AssignmentCategoriesDao, AssignmentCategoriesEntity> implements AssignmentCategoriesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AssignmentCategoriesEntity> page = this.page(
                new Query<AssignmentCategoriesEntity>().getPage(params),
                new QueryWrapper<AssignmentCategoriesEntity>()
        );

        return new PageUtils(page);
    }

}