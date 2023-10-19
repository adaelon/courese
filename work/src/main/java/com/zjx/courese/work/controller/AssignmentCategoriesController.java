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

import com.zjx.courese.work.entity.AssignmentCategoriesEntity;
import com.zjx.courese.work.service.AssignmentCategoriesService;
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
@RequestMapping("work/assignmentcategories")
public class AssignmentCategoriesController {
    @Autowired
    private AssignmentCategoriesService assignmentCategoriesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("work:assignmentcategories:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = assignmentCategoriesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{categoryId}")
    //@RequiresPermissions("work:assignmentcategories:info")
    public R info(@PathVariable("categoryId") Integer categoryId){
		AssignmentCategoriesEntity assignmentCategories = assignmentCategoriesService.getById(categoryId);

        return R.ok().put("assignmentCategories", assignmentCategories);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("work:assignmentcategories:save")
    public R save(@RequestBody AssignmentCategoriesEntity assignmentCategories){
		assignmentCategoriesService.save(assignmentCategories);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("work:assignmentcategories:update")
    public R update(@RequestBody AssignmentCategoriesEntity assignmentCategories){
		assignmentCategoriesService.updateById(assignmentCategories);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("work:assignmentcategories:delete")
    public R delete(@RequestBody Integer[] categoryIds){
		assignmentCategoriesService.removeByIds(Arrays.asList(categoryIds));

        return R.ok();
    }

}
