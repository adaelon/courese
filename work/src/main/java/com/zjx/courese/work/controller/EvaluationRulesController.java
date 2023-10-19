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
@RequestMapping("work/evaluationrules")
public class EvaluationRulesController {
    @Autowired
    private EvaluationRulesService evaluationRulesService;

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

        return R.ok();
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
