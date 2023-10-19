package com.zjx.courese.peerevaluation.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zjx.courese.peerevaluation.entity.AutoScoresEntity;
import com.zjx.courese.peerevaluation.service.AutoScoresService;
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
@RequestMapping("peerevaluation/autoscores")
public class AutoScoresController {
    @Autowired
    private AutoScoresService autoScoresService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("peerevaluation:autoscores:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = autoScoresService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{autoScoreId}")
    //@RequiresPermissions("peerevaluation:autoscores:info")
    public R info(@PathVariable("autoScoreId") Integer autoScoreId){
		AutoScoresEntity autoScores = autoScoresService.getById(autoScoreId);

        return R.ok().put("autoScores", autoScores);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("peerevaluation:autoscores:save")
    public R save(@RequestBody AutoScoresEntity autoScores){
		autoScoresService.save(autoScores);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("peerevaluation:autoscores:update")
    public R update(@RequestBody AutoScoresEntity autoScores){
		autoScoresService.updateById(autoScores);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("peerevaluation:autoscores:delete")
    public R delete(@RequestBody Integer[] autoScoreIds){
		autoScoresService.removeByIds(Arrays.asList(autoScoreIds));

        return R.ok();
    }

}
