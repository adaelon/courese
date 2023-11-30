package com.zjx.courese.peerevaluation.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjx.common.utils.PageUtils;
import com.zjx.common.utils.Query;

import com.zjx.courese.peerevaluation.dao.PeerReviewAssignmentsDao;
import com.zjx.courese.peerevaluation.entity.PeerReviewAssignmentsEntity;
import com.zjx.courese.peerevaluation.service.PeerReviewAssignmentsService;


@Service("peerReviewAssignmentsService")
public class PeerReviewAssignmentsServiceImpl extends ServiceImpl<PeerReviewAssignmentsDao, PeerReviewAssignmentsEntity> implements PeerReviewAssignmentsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PeerReviewAssignmentsEntity> page = this.page(
                new Query<PeerReviewAssignmentsEntity>().getPage(params),
                new QueryWrapper<PeerReviewAssignmentsEntity>()
        );

        return new PageUtils(page);
    }
    @Override
    public int updateStatus(Integer reviewerId, Integer submissionId, Integer status) {
        // 构建更新条件并执行更新
        return lambdaUpdate()
                .eq(PeerReviewAssignmentsEntity::getReviewerId, reviewerId)
                .eq(PeerReviewAssignmentsEntity::getSubmissionId, submissionId)
                .set(PeerReviewAssignmentsEntity::getStatus, status)
                .update() ? 1 : 0;
    }


    @Override
    public  PageUtils queryUserWork(Map<String, Object> params){
        Object userIdObj = params.get("userId");
        Integer userId = null;

        if (userIdObj instanceof Integer) {
            userId = (Integer) userIdObj;
        } else if (userIdObj instanceof String) {
            userId = Integer.parseInt((String) userIdObj);
        }


        String type = (String)params.get("type");
                 int status= 0 ;
                 if(type.equals("全部")){

                     IPage<PeerReviewAssignmentsEntity> page = this.page(
                             new Query<PeerReviewAssignmentsEntity>().getPage(params),
                             new QueryWrapper<PeerReviewAssignmentsEntity>()
                                     .eq("reviewer_id",userId)
                     );

                     return new PageUtils(page);
                 }else if(type.equals("未完成")){
                     status = 0;
                 }else if(type.equals("已完成")){
                     status = 1;
                 }else if(type.equals("已失效")){
                     status = 2;
                 }

                 IPage<PeerReviewAssignmentsEntity> page = this.page(
                         new Query<PeerReviewAssignmentsEntity>().getPage(params),
                         new QueryWrapper<PeerReviewAssignmentsEntity>()
                                 .eq("reviewer_id",userId)
                                 .eq("status",status)
                 );

                 return new PageUtils(page);
    }
}