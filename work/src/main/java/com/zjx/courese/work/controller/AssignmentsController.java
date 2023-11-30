package com.zjx.courese.work.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.zjx.courese.work.feign.AnomaliesFeignService;
import com.zjx.courese.work.vo.AnomaliesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zjx.courese.work.entity.AssignmentsEntity;
import com.zjx.courese.work.service.AssignmentsService;
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
@RequestMapping("work/assignments")
public class AssignmentsController {
    @Autowired
    private AssignmentsService assignmentsService;

    @Autowired
    AnomaliesFeignService anomaliesFeignService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("work:assignments:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = assignmentsService.queryPage(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/worklist/{courseId}")
    //@RequiresPermissions("work:assignments:info")
    public R info(@PathVariable("courseId") Integer courseId,
                  @RequestParam Map<String, Object> params){
       PageUtils page = assignmentsService.queryAssignmentsPageByCourseId(params, courseId);

        return R.ok().put("assignments", page);
    }
    /**
     * 信息
     */
    @RequestMapping("/info/{assignmentId}")
    //@RequiresPermissions("work:assignments:info")
    public R info(@PathVariable("assignmentId") Integer assignmentId){
		AssignmentsEntity assignments = assignmentsService.getById(assignmentId);

        return R.ok().put("assignments", assignments);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("work:assignments:save")
    public R save(@RequestBody AssignmentsEntity assignments){
		assignmentsService.save(assignments);

        return R.ok().put("assignment", assignments);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("work:assignments:update")
    public R update(@RequestBody AssignmentsEntity assignments){
		assignmentsService.updateById(assignments);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("work:assignments:delete")
    public R delete(@RequestBody Integer[] assignmentIds){
		assignmentsService.removeByIds(Arrays.asList(assignmentIds));

        return R.ok();
    }

}
