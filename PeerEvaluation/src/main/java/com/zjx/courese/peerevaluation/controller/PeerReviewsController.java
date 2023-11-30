package com.zjx.courese.peerevaluation.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjx.courese.peerevaluation.service.PeerReviewAssignmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zjx.courese.peerevaluation.entity.PeerReviewsEntity;
import com.zjx.courese.peerevaluation.service.PeerReviewsService;
import com.zjx.common.utils.PageUtils;
import com.zjx.common.utils.R;



/**
 * 
 *
 * @author zjx
 * @email zjxlijo@gmail.com
 * @date 2023-10-19 20:06:29
 */
@RestController
@RequestMapping("peerevaluation/peerreviews")
public class PeerReviewsController {
    @Autowired
    private PeerReviewsService peerReviewsService;
    @Autowired
    private PeerReviewAssignmentsService peerReviewAssignmentsService;


    @RequestMapping("/content")
    public List<PeerReviewsEntity> content(@RequestParam Map<String,Object> params){
        Integer submissionId= Integer.parseInt((String)params.get("submissionId"));
//查询
        QueryWrapper<PeerReviewsEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("submission_id",submissionId);
        List<PeerReviewsEntity> resultList = peerReviewsService.list(queryWrapper);

        return resultList;

    }
    //创建评阅
    @RequestMapping("/createReview")
    public R createReview(@RequestBody Map<String,Object> params){
        System.out.println(params);
        Object submissionIdObj = params.get("submissionId");
        Integer submissionId = null;

        if (submissionIdObj instanceof Integer) {
            submissionId = (Integer) submissionIdObj;
        } else if (submissionIdObj instanceof String) {
            submissionId = Integer.parseInt((String) submissionIdObj);
        }
        //从params获取相关参数
        Object reviewerIdObj = params.get("reviewerId");
        Integer reviewerId = null;

        if (reviewerIdObj instanceof Integer) {
            reviewerId = (Integer) reviewerIdObj;
        } else if (reviewerIdObj instanceof String) {
            reviewerId = Integer.parseInt((String) reviewerIdObj);
        }

        Object scoreObj = params.get("score");
        Integer scoreI = null;

        if (scoreObj instanceof Integer) {
            scoreI = (Integer) scoreObj;
        } else if (scoreObj instanceof String) {
            scoreI = Integer.parseInt((String) scoreObj);
        }
        BigDecimal score = new BigDecimal(scoreI);
        String feedback = (String)params.get("feedback");

        //构建实体
        PeerReviewsEntity peerReviewsEntity = new PeerReviewsEntity();
        peerReviewsEntity.setSubmissionId(submissionId);
        peerReviewsEntity.setReviewerId(reviewerId);
        peerReviewsEntity.setScore(score);
        peerReviewsEntity.setFeedback(feedback);
        peerReviewsEntity.setReviewDate(new Date());


        //需要根据submissionId修改状态为已完成（未写）
        peerReviewAssignmentsService.updateStatus(reviewerId, submissionId, 1);

        //保存到数据库中
        peerReviewsService.save(peerReviewsEntity);

        //向前端返回消息
        return R.ok("评价成功！");


    }
    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("peerevaluation:peerreviews:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = peerReviewsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{reviewId}")
    //@RequiresPermissions("peerevaluation:peerreviews:info")
    public R info(@PathVariable("reviewId") Integer reviewId){
		PeerReviewsEntity peerReviews = peerReviewsService.getById(reviewId);

        return R.ok().put("peerReviews", peerReviews);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("peerevaluation:peerreviews:save")
    public R save(@RequestBody PeerReviewsEntity peerReviews){
		peerReviewsService.save(peerReviews);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("peerevaluation:peerreviews:update")
    public R update(@RequestBody PeerReviewsEntity peerReviews){
		peerReviewsService.updateById(peerReviews);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("peerevaluation:peerreviews:delete")
    public R delete(@RequestBody Integer[] reviewIds){
		peerReviewsService.removeByIds(Arrays.asList(reviewIds));

        return R.ok();
    }

}
