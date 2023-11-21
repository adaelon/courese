package com.zjx.courese.work.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjx.courese.work.entity.AssignmentsEntity;
import com.zjx.courese.work.entity.EvaluationRulesEntity;
import com.zjx.courese.work.service.AssignmentsService;
import com.zjx.courese.work.service.EvaluationRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zjx.courese.work.entity.SubmissionsEntity;
import com.zjx.courese.work.service.SubmissionsService;
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
@RequestMapping("work/submissions")
public class SubmissionsController {
    @Autowired
    private SubmissionsService submissionsService;

    @Autowired
    private AssignmentsService assignmentsService;

    @Autowired
    private EvaluationRulesService evaluationRulesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("work:submissions:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = submissionsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{submissionId}")
    //@RequiresPermissions("work:submissions:info")
    public R info(@PathVariable("submissionId") Integer submissionId){
        //获得当前学生提交作业的详细内容
		SubmissionsEntity submissions = submissionsService.getById(submissionId);
        Integer assignmentId = submissions.getAssignmentId();
        //获得当前作业的详细要求
        AssignmentsEntity  assignments = assignmentsService.getById(assignmentId);
        QueryWrapper<EvaluationRulesEntity> queryWrapper = new QueryWrapper<>();
        //构造查询条件
        queryWrapper.eq("assignment_id",assignmentId);
        //获得作业的评价规则
        EvaluationRulesEntity evaluationRules= evaluationRulesService.getOne(queryWrapper);

        Map<String, Object> map = new HashMap<String,Object>();
        map.put("submissions",submissions);
        map.put("assignments",assignments);
        map.put("evaluationRules",evaluationRules);
        return R.ok().put("data", map);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("work:submissions:save")
    public R save(@RequestBody SubmissionsEntity submissions){
		submissionsService.save(submissions);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("work:submissions:update")
    public R update(@RequestBody SubmissionsEntity submissions){
		submissionsService.updateById(submissions);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("work:submissions:delete")
    public R delete(@RequestBody Integer[] submissionIds){
		submissionsService.removeByIds(Arrays.asList(submissionIds));

        return R.ok();
    }

}
