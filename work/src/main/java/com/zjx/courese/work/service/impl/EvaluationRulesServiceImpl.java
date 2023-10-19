package com.zjx.courese.work.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjx.common.utils.PageUtils;
import com.zjx.common.utils.Query;

import com.zjx.courese.work.dao.EvaluationRulesDao;
import com.zjx.courese.work.entity.EvaluationRulesEntity;
import com.zjx.courese.work.service.EvaluationRulesService;


@Service("evaluationRulesService")
public class EvaluationRulesServiceImpl extends ServiceImpl<EvaluationRulesDao, EvaluationRulesEntity> implements EvaluationRulesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<EvaluationRulesEntity> page = this.page(
                new Query<EvaluationRulesEntity>().getPage(params),
                new QueryWrapper<EvaluationRulesEntity>()
        );

        return new PageUtils(page);
    }

}