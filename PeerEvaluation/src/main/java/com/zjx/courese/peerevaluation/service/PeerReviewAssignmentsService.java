package com.zjx.courese.peerevaluation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjx.common.utils.PageUtils;
import com.zjx.courese.peerevaluation.entity.PeerReviewAssignmentsEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 
 *
 * @author zjx
 * @email zjxlijo@gmail.com
 * @date 2023-10-19 20:06:29
 */
public interface PeerReviewAssignmentsService extends IService<PeerReviewAssignmentsEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryUserWork(Map<String, Object> params);
    int updateStatus(@Param("reviewerId") Integer reviewerId,
                     @Param("submissionId") Integer submissionId,
                     @Param("status") Integer status);
}

