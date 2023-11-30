package com.zjx.courese.work.controller;

import java.util.*;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.zjx.courese.work.entity.AssignmentsEntity;
import com.zjx.courese.work.entity.SubmissionsEntity;
import com.zjx.courese.work.feign.AnomaliesFeignService;
import com.zjx.courese.work.feign.CourseFeignService;
import com.zjx.courese.work.service.AssignmentsService;
import com.zjx.courese.work.service.SubmissionsService;
import com.zjx.courese.work.vo.AnomaliesVO;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zjx.courese.work.entity.EvaluationRulesEntity;
import com.zjx.courese.work.service.EvaluationRulesService;
import com.zjx.common.utils.PageUtils;
import com.zjx.common.utils.R;



/**
 * 
 *
 * @author zjx
 * @email zjxlijo@gmail.com
 * @date 2023-10-19 11:56:34
 */
@RestController
@RefreshScope
@RequestMapping("work/evaluationrules")
public class EvaluationRulesController {
    @Autowired
    private EvaluationRulesService evaluationRulesService;


    @Autowired
    AnomaliesFeignService anomaliesFeignService;

    @Autowired
    CourseFeignService courseFeignService;

    @Autowired
    AssignmentsService assignmentsService;

    @Autowired
    SubmissionsService submissionsService;

    private Integer assignmentId;

    int reviewsCount;
    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("work:evaluationrules:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = evaluationRulesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{ruleId}")
    //@RequiresPermissions("work:evaluationrules:info")
    public R info(@PathVariable("ruleId") Integer ruleId){
		EvaluationRulesEntity evaluationRules = evaluationRulesService.getById(ruleId);

        return R.ok().put("evaluationRules", evaluationRules);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("work:evaluationrules:save")
    public R save(@RequestBody EvaluationRulesEntity evaluationRules){
		evaluationRulesService.save(evaluationRules);
        AnomaliesVO anomaliesVO = new AnomaliesVO();

        assignmentId=evaluationRules.getAssignmentId();
        reviewsCount = evaluationRules.getPeerNum();
        System.out.println(evaluationRules.getDueDate());
        assignPeerReviews();
        //anomaliesFeignService.save();
        return R.ok();
    }



    @GlobalTransactional(rollbackFor = Exception.class)
    public void assignPeerReviews() {
        List<Integer> userIds = new ArrayList<>();


        AssignmentsEntity assignments = assignmentsService.getById(assignmentId);
        Integer courseId= assignments.getCourseId();
        //System.out.println("courseId:"+courseId);

        // 调用 courseUser 方法获取远程服务的响应
        Map<String, Object> params = new HashMap<>();
        R response = courseFeignService.courseUser(courseId, params);
        Map<String, Object> userList1 =  (Map<String, Object>) response.get("page");
        System.out.println( userList1.get("list"));

        // 处理响应，提取 userId 放入数组
        if (response != null ) {
            List<Map<String, Object>> userList = (List<Map<String, Object>>) userList1.get("list");
            for (Map<String, Object> user : userList) {
                Integer userId = (Integer) user.get("userId");
                if (userId != null) {
                    userIds.add(userId);
                }
            }
        }


        //PageUtils page = submissionsService.querySubAss(params,assignmentId);
        PageUtils page = submissionsService.querySubAss(params,1);
        System.out.println("page"+page);
        List<SubmissionsEntity> list = (List<SubmissionsEntity>) page.getList();
        System.out.println("sub"+list);
        List<Integer> submissionIds = new ArrayList<>(); // 创建一个用于存储submissionId的新列表
        for (SubmissionsEntity entity : list) {
            submissionIds.add(entity.getSubmissionId());
        }

        Map<Integer, int[]> peerReviewAssignments = new HashMap<>();

        for (int k = 0; k < userIds.size(); k++) {
            int[] assignedReviews = new int[reviewsCount];
            AnomaliesVO anomaliesVO =new AnomaliesVO();
            anomaliesVO.setReviewerId(userIds.get(k));
            System.out.println("userIds"+anomaliesVO.getReviewerId());
            anomaliesVO.setStatus(0);
            anomaliesVO.setAssignmentId(1);
            for (int j = 0; j < reviewsCount; j++) {
                assignedReviews[j] = submissionIds.get((k + j + 1) % submissionIds.size());
                anomaliesVO.setSubmissionId( assignedReviews[j]);

                anomaliesFeignService.save(anomaliesVO);
            }
            peerReviewAssignments.put(k, assignedReviews);
        }


    }
    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("work:evaluationrules:update")
    public R update(@RequestBody EvaluationRulesEntity evaluationRules){
		evaluationRulesService.updateById(evaluationRules);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("work:evaluationrules:delete")
    public R delete(@RequestBody Integer[] ruleIds){
		evaluationRulesService.removeByIds(Arrays.asList(ruleIds));

        return R.ok();
    }

}
