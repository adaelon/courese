package com.zjx.courese.work.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
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
		SubmissionsEntity submissions = submissionsService.getById(submissionId);

        return R.ok().put("submissions", submissions);
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
